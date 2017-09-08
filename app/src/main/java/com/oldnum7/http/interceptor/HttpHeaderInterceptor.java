package com.oldnum7.http.interceptor;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.oldnum7.Constants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/05/19:29
 *       desc   : 服务器加密认证: http://www.cnblogs.com/bestzrz/archive/2011/09/03/2164620.html
 *       1. header传输: timeStamp,token,formBody 排序后,以=&形式连接,用给定的ANDROID_SECRET_KEY(约定的)用Mac sha256 加密->base64加密,生成signkey ,放在header里传输,header里面还有其他的字段...
 *       2. params传输: 将所有的参数加密成key,拼接在url后...timeline,uuid,token,ANDROID_SECRET_KEY,以=&形式连接,用md5加密,作为一个signRrjcMsg,在以=&形式连接其他参数,uuid,version,表单的参数等,
 *       转换成jsonString文本,用Des3指定的秘钥加密,以下形式.url?key = "-------"拼接; 该方式服务端不规范...
 *       version: 1.0
 * </pre>
 */

public class HttpHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request.Builder builder = originRequest.newBuilder();

        LinkedHashMap<String, String> headers = new LinkedHashMap();
        //1. 添加header头...
        String timeStamp = System.currentTimeMillis() + "";
        headers.put("timeStamp", timeStamp);
        headers.put("clientType", "2");
        headers.put("clientVersion", AppUtils.getAppVersionName());
        headers.put("imei", PhoneUtils.getIMEI());
        headers.put("deviceSystem", PhoneUtils.getIMEI());
        headers.put("deviceDpi", PhoneUtils.getIMEI());
        headers.put("deviceType", DeviceUtils.getModel());
        headers.put("netType", NetworkUtils.getNetworkType().toString());
        headers.put("netType", PhoneUtils.getIMEI());
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                //对头信息进行 utf-8 编码,防止头信息传中文,这里暂时不编码,可能出现未知问题,如有需要自行编码
//                String headerValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                builder.header(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2. 签名参数
        Map<String, String> sign = new HashMap<>();
        sign.put("clientType", "2");
        sign.put("timeStamp", timeStamp);
        sign.put("clientVersion", AppUtils.getAppVersionName());

        if (originRequest.body() instanceof FormBody) {
            //拦截form请求的参数...
            FormBody originFormBody = (FormBody) originRequest.body();
            if (originFormBody != null && originFormBody.size() > 0) {
                for (int i = 0; i < originFormBody.size(); i++) {
                    sign.put(originFormBody.name(i), originFormBody.value(i));
                }
            }
        } else if (originRequest.body() instanceof MultipartBody) {
            MultipartBody originFormBody = (MultipartBody) originRequest.body();
            //拦截MultipartBody请求的参数...无法拦截....这是一个流,服务端要求在这里拦截,一点也不规范...
            List<MultipartBody.Part> parts = originFormBody.parts();
            for (int i = 0; i < parts.size(); i++) {
                MultipartBody.Part part = parts.get(i);
                Headers header = part.headers();
                String name = header.get("name");

                RequestBody body = part.body();
                MediaType mediaType = body.contentType();

                Buffer sink = new Buffer();
                body.writeTo(sink);
                Log.e("header", "name: " + name + "/context" + sink.toString());
            }
        }

        String token = "";
        if (!TextUtils.isEmpty(token)) {
            sign.put("token", token);
            //TODO... token放在Header进行传输..?????
            builder.header("token", token);
        }

        // 对参数先进行排序，然后加密
        List<Map.Entry<String, String>> parasList = sort(sign);
        String key = sign(parasList);

        // sign放在Header进行传输
        builder.header("sign", key);

        //-------------------------添加查询参数...-------------------------------------//
//        HttpUrl httpUrl = originRequest.url().newBuilder().addQueryParameter("key", key).build();
//        Request request = builder.url(httpUrl).build();
        //-------------------------添加查询参数...-------------------------------------//


        Request finalRequest = builder.build();
        Response response = chain.proceed(finalRequest);
        //拦截响应头的token...
//        String resToken = response.header("token");

        return response;
    }

    private List<Map.Entry<String, String>> sort(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        //字符串顺序排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        return infoIds;
    }

    private String sign(List<Map.Entry<String, String>> list) {

        if (list == null || list.size() == 0) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                builder.append(list.get(i).getKey().trim() + "=" + list.get(i).getValue().trim() + "&");
            } else {
                builder.append(list.get(i).getKey().trim() + "=" + list.get(i).getValue().trim());
            }
        }

        String sign = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(Constants.ANDROID_SECRET_KEY.getBytes("UTF-8"), mac.getAlgorithm());
            mac.init(secret);
            byte[] hash = mac.doFinal(builder.toString().getBytes("UTF-8"));
            sign = Base64.encodeToString(hash, Base64.DEFAULT).trim();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

}
