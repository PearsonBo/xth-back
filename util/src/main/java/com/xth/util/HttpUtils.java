package com.xth.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.FormBody.Builder;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/19 0011 下午 22:05
 */
@Slf4j
public class HttpUtils {

    /**
     * Http Post
     *
     * @param url
     * @param form
     * @return
     */
    public static String doGet(String url, Map<String, String> parms) {
        StringBuilder paramBuilder = new StringBuilder();
        if (parms != null && parms.size() > 0) {
            for (String name : parms.keySet()) {
                paramBuilder.append("&").append(name).append("=").append(parms.get(name));
            }
        }
        String paramStr = paramBuilder.toString();

        String okUrl = url + ((url.indexOf("?") != -1) ? paramStr : ("?" + paramStr));
        log.info("url:" + okUrl);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(okUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * Http Post
     *
     * @param url
     * @param form
     * @return
     */
    public static String doPost(String url, Map<String, String> form) {
        OkHttpClient client = new OkHttpClient();
        Builder formBodyBuilder = new FormBody.Builder();
        if (form != null) {
            form.forEach((name, value) -> {
                if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
                    return;
                }
                formBodyBuilder.add(name, value);
            });
        }
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static Map postJson(String url, String data) {
        log.info("post请求的url为:" + url);
        Map retMap = Maps.newHashMap();
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, data);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                retMap.put("code", 200);
                retMap.put("data", response.body().string());
                return retMap;
            }
        } catch (IOException e) {
            log.error(String.format("post请求失败, url:%s,原因：%s", url, e));
        }
        return null;
    }
}
