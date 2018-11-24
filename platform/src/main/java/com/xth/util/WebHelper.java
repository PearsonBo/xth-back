package com.xth.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author admin
 */
public class WebHelper {

    private WebHelper() {

    }

    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    private static final String AJAX_UPLOAD = "ajaxUpload";

    private static final String HTTP = "http://";

    private static final String UTF8 = "utf-8";

    private static final String EMPTY = "";

    private static final String QUESTION = "?";

    public static String urlEncode(String param) {
        try {
            return URLEncoder.encode(param, UTF8);
        } catch (Exception e) {
            return param;
        }
    }

    public static String urlDecode(String param) {
        try {
            return URLDecoder.decode(param, UTF8);
        } catch (Exception e) {
            return param;
        }
    }

    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader(X_REQUESTED_WITH);
        return requestedWith != null ? XML_HTTP_REQUEST.equals(requestedWith) : false;
    }

    public static boolean isAjaxRequest(HttpServletRequest webRequest) {
        String requestedWith = webRequest.getHeader(X_REQUESTED_WITH);
        return requestedWith != null ? XML_HTTP_REQUEST.equals(requestedWith) : false;
    }

    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter(AJAX_UPLOAD) != null;
    }

    public static String getActionUrl(WebRequest request, String controllerName, String actionName) {
        return request.getContextPath() + controllerName + actionName;
    }

    public static String getActionUrl(HttpServletRequest request, String controllerName, String actionName) {
        return request.getContextPath() + controllerName + actionName;
    }

    public static String getFullUrl(HttpServletRequest request) {
        String params = StringUtils.isEmpty(request.getQueryString()) ? EMPTY : QUESTION + request.getQueryString();
        return HTTP + request.getServerName() //服务器地址
                + request.getContextPath()      //项目名称
                + request.getServletPath()      //请求页面或其他地址
                + params;//参数
    }

    public static int tryParseInt(String str, int def) {
        if (StringUtils.isEmpty(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

}
