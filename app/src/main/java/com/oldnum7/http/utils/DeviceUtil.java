package com.oldnum7.http.utils;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;

/**
 *  设备信息工具
 * 
 * @author zuoqx
 * Created at 2016/8/30 14:29
*/
public class DeviceUtil {

    public static String getAndroidID(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getIMEI(Context ctx) {
        return ((TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getIMSI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId() != null ? tm.getSubscriberId() : null;
    }

    @SuppressWarnings("MissingPermission")
    public static String getWifiMacAddr(Context ctx) {
        String macAddr = "";
        try {
            WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            macAddr = wifi.getConnectionInfo().getMacAddress();
            if (macAddr == null) {
                macAddr = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddr;
    }

    /**
     *  return unique device id
     * @param ctx
     * @return
     */
    public static String getDeviceId(Context ctx) {

        String imei = getIMEI(ctx);
        String imsi = getIMSI(ctx);
        String macAddess = getWifiMacAddr(ctx);
        String androidId = getAndroidID(ctx);
        String customDeviceId = "35" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits

        if (!android.text.TextUtils.isEmpty(imei)) {
            return imei;
        } else if (!android.text.TextUtils.isEmpty(imsi)) {
            return imsi;
        } else if (!android.text.TextUtils.isEmpty(macAddess)) {
            return macAddess;
        } else if (!android.text.TextUtils.isEmpty(androidId)) {
            return androidId;
        } else {
            return customDeviceId;
        }

    }

    public static String getIP(Context ctx) {
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled() ? getWifiIP(wifiManager) : getGPRSIP();
    }


    public static String getWifiIP(WifiManager wifiManager) {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = intToIp(wifiInfo.getIpAddress());
        return ip != null ? ip : "";
    }

    public static String getGPRSIP() {
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                for (Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            ip = null;
        }
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }


    public static String getSerial() {
        return Build.SERIAL;
    }

    public static String getSIMSerial(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    public static String getPhoneNumber(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    public static String getMNC(Context ctx) {
        String providersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
            providersName = telephonyManager.getSimOperator();
            providersName = providersName == null ? "" : providersName;
        }
        return providersName;
    }

    public static String getCarrier(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
    }


    public static String getModel() {
        return Build.MODEL;
    }

    public static String getBuildBrand() {
        return Build.BRAND;
    }

    public static String getBuildHost() {
        return Build.HOST;
    }

    public static String getBuildTags() {
        return Build.TAGS;
    }

    public static long getBuildTime() {
        return Build.TIME;
    }

    public static String getBuildUser() {
        return Build.USER;
    }

    public static String getBuildVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getBuildVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    public static String getBuildVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    public static int getBuildVersionSDK() {
        return Build.VERSION.SDK_INT;
    }

    public static String getBuildID() {
        return Build.ID;
    }

    public static String[] getSupportedABIS() {
        String[] result = new String[]{"-"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = Build.SUPPORTED_ABIS;
        }
        if (result == null || result.length == 0) {
            result = new String[]{"-"};
        }
        return result;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }


    public static String getBootloader() {
        return Build.BOOTLOADER;
    }


    public static String getScreenDisplayID(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return String.valueOf(wm.getDefaultDisplay().getDisplayId());
    }

    public static String getDisplayVersion() {
        return Build.DISPLAY;
    }


    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getCountry(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        Locale locale = Locale.getDefault();
        return tm.getSimState() == TelephonyManager.SIM_STATE_READY ? tm.getSimCountryIso().toLowerCase(Locale.getDefault()) : locale.getCountry().toLowerCase(locale);
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
     */
    public static String getGSFID(Context context) {
        String result;
        final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        final String ID_KEY = "android_id";
        String[] params = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (c == null || !c.moveToFirst() || c.getColumnCount() < 2) {
            return null;
        } else {
            result = Long.toHexString(Long.parseLong(c.getString(1)));
        }
        c.close();
        return result;
    }

    /**
     * <uses-permission android:name="android.permission.BLUETOOTH"/>
     */
    @SuppressWarnings("MissingPermission")
    public static String getBluetoothMAC(Context context) {
        String result = null;
        try {
            if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
                    == PackageManager.PERMISSION_GRANTED) {
                BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                result = bta.getAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPsuedoUniqueID() {
        String devIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            devIDShort += (Build.SUPPORTED_ABIS[0].length() % 10);
        } else {
            devIDShort += (Build.CPU_ABI.length() % 10);
        }
        devIDShort += (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception e) {
            serial = "ESYDV000";
        }
        return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
    }

    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getRadioVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? Build.getRadioVersion() : "";
    }

    public static String getUA(Context ctx) {
        final String system_ua = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return new WebView(ctx).getSettings().getDefaultUserAgent(ctx) + "__" + system_ua;
        } else {
            return new WebView(ctx).getSettings().getUserAgentString() + "__" + system_ua;
        }
    }


    /**
     * ldpi（低）~120dpi
     * mdpi（中）~160dpi
     * hdpi（高）~240dpi
     * xhdpi（超高）~320dpi
     * xxhdpi（超超高）~480dpi
     * xxxhdpi（超超超高）~640dpi
     * @param ctx
     * @return
     */
    public static String getDensity(Context ctx) {
        String densityStr = null;
        final int density = ctx.getResources().getDisplayMetrics().densityDpi;

        if (density <= DisplayMetrics.DENSITY_LOW) {
            densityStr = "ldpi";
        } else if (density > DisplayMetrics.DENSITY_LOW && density <= DisplayMetrics.DENSITY_MEDIUM) {
            densityStr = "mdpi";
        } else if (density > DisplayMetrics.DENSITY_MEDIUM && density <= DisplayMetrics.DENSITY_HIGH) {
            densityStr = "hdpi";
        } else if (density > DisplayMetrics.DENSITY_HIGH && density <= DisplayMetrics.DENSITY_XHIGH) {
            densityStr = "xhdpi";
        } else if (density > DisplayMetrics.DENSITY_XHIGH && density <= DisplayMetrics.DENSITY_XXHIGH) {
            densityStr = "xxhdpi";
        } else if (density > DisplayMetrics.DENSITY_XXHIGH && density <= DisplayMetrics.DENSITY_XXXHIGH) {
            densityStr = "xxxhdpi";
        } else {
            densityStr = "unknown";
        }

        return densityStr;
    }

    /**
     * <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
     */
    @SuppressWarnings("MissingPermission")
    public static String[] getGoogleAccounts(Context ctx) {
        if (ctx.checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = AccountManager.get(ctx).getAccountsByType("com.google");
            String[] result = new String[accounts.length];
            for (int i = 0; i < accounts.length; i++) {
                result[i] = accounts[i].name;
            }
            return result;
        }
        return null;
    }

}
