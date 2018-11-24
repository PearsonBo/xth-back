package com.xth.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/28 0028 下午 20:23
 */
@Slf4j
public class LocationUtil {

    private LocationUtil() {

    }

    public static void main(String[] args) {
        String lo = "120.1467261951";
        String la = "30.3223730035";

        String location = LocationUtil.getLocation(lo, la);
        System.out.println(location);
    }
    public static String getLocation(String longitude, String latitude) {
        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + latitude + "," + longitude + "&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            log.error("error in wapaction,and e is " + e.getMessage());
        }
        return res;
    }
}
