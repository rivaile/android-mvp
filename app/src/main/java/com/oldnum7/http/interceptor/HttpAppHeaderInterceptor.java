package com.oldnum7.http.interceptor;

import android.util.Base64;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author
 *         Created at 2016/11/12 15:51
 */
public class HttpAppHeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
            String timeStamp = System.currentTimeMillis() + "";

            Request originRequest = chain.request();
            Request.Builder builder = originRequest.newBuilder();

            builder.header("timeStamp", timeStamp)
                    .header("clientType", "2");


            builder.header("Content-Type", "application/x-www-form-urlencoded");

            Map<String, String> signParasMap = new HashMap<>();
            signParasMap.put("clientType", "2");
            signParasMap.put("timeStamp", timeStamp);


            if (originRequest.body() instanceof FormBody) {
                FormBody originFormBody = (FormBody) originRequest.body();

                if (originFormBody != null && originFormBody.size() > 0) {
                    for (int i = 0; i < originFormBody.size(); i++) {
                        signParasMap.put(originFormBody.name(i), originFormBody.value(i));
                    }
                }

            }

            // 获取token
    //        String token = UserManager.getInstance().getToken();



    //        if (!TextUtils.isEmpty(token)) {
    //            signParasMap.put("token", token);
    //
    //            // token放在Header进行传输
    //            builder.header("token", token);
    //        }


            // 对参数先进行排序，然后加密
            List<Map.Entry<String, String>> parasList = sort(signParasMap);
            String sign = sign(parasList);

            // sign放在Header进行传输
            builder.header("sign", sign);


            /*try {
                // 对请求参数DES加密
                String key = null ;
                Logger.d("key= " + key);
                FormBody.Builder postData = new FormBody.Builder();
                postData.add("key", key);
                builder.post(postData.build());
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            Request finalRequest = builder.build();

            Response response = chain.proceed(finalRequest);

            // 先判断网络，网络好的时候，移除header后添加haunch失效时间为1小时，网络未连接的情况下设置缓存时间为7天
                            /*if (NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
                                int maxAge = 60 * 60; // read from cache for 60 minute
                                response.newBuilder()
                                        .removeHeader("Pragma")
                                        .header("Cache-Control", "public, max-age=" + maxAge)
                                        .build();
                            } else {
                                int maxStale = 60 * 60 * 24 * 7; // tolerate 4-weeks stale
                                response.newBuilder()
                                        .removeHeader("Pragma")
                                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                        .build();

                            }*/
            HashMap<String, String> otherInfo = new HashMap<>();
            otherInfo.put("URI", finalRequest.url().toString());
            otherInfo.put("parameter", sign);
            otherInfo.put("requestStartTime", timeStamp);
            otherInfo.put("requestEndTime", System.currentTimeMillis() + "");
            return response;
    }

    private List<Map.Entry<String, String>> sort(Map<String, String> map) {

        if (map == null || map.size() == 0) {

            return null;
        }

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        //排序前
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
//            System.out.println(id);
        }

//        System.out.println("------------");

        //排序+
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
//            System.out.println(id);
        }

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



        Mac mac = null;
        String sign = null;

        try {
            mac = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secret = new SecretKeySpec(AppConstants.ANDROID_SECRET_KEY.getBytes("UTF-8"), mac.getAlgorithm());
//            mac.init(secret);
            byte[] hash = mac.doFinal(builder.toString().getBytes("UTF-8"));
            sign = Base64.encodeToString(hash, Base64.DEFAULT).trim();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Logger.d("sign -------" + sign);
        return sign;
    }

}
