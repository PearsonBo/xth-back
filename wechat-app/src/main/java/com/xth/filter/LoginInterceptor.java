package com.xth.filter;

import com.google.common.collect.ImmutableSet;
import com.xth.dao.client.ClientDao;
import com.xth.model.bo.client.Client;
import com.xth.model.constant.CommonConstant;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.service.RedisReadHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/27 0027 下午 22:19
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String BIG_FILE = "http://download.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32-x86_64.zip";
    //Set<String> needLoginPath = ImmutableSet.<String>builder()
    //        .add("/rest/client/fillMobile")
    //        .add("/rest/client/fillAddressByCityId")
    //        .add("/rest/client/fillAddressByDetail")
    //        .add("/rest/clientCoupon/listPagination")
    //        .add("/rest/clientCoupon/receiveCoupon")
    //        .add("/rest/clientCoupon/listPagination")
    //        .add("/rest/store/listPagination")
    //        .add("/rest/rebate/create")
    //        .build();

    Set<String> safePath = ImmutableSet.<String>builder()
            .add("/rest/login/onLogin")
            .add("/rest/rebate/weChat/uploadImage")
            .add("/rest/article/listPagination")
            .add("/rest/store/listPagination")
            .add("/rest/city/listPagination")
            .build();

    @Value("${login.switch}")
    private Boolean loginSwitch;

    @Autowired
    private RedisReadHelper redisReadHelper;

    @Autowired
    private ClientDao clientDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long start = System.currentTimeMillis();
        String requestPath = request.getRequestURI();

        log.info("请求url:" + requestPath);
        /*
         * Wechat接口
    	 */
        if (isApp(requestPath)) {
            log.info("## 1. 安全请求检测");
            if (!loginSwitch) {
                log.info("登录校验开关关闭，不需要登录");
                return true;
            }
            if (safePath.contains(requestPath)) {
                log.info("安全请求" + requestPath);
                return true;
            }

            log.info("## 2. 是否登录");
            String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);

            if (StringUtils.isEmpty(thirdSession)) {
                log.error("未登陆");
                throw new BizException("未登陆", ExceptionType.NOT_LOGIN);
            }

            String clientIdInRedis = redisReadHelper.getStringInRedis(thirdSession);

            if (StringUtils.isEmpty(clientIdInRedis)) {
                log.error("未登陆");
                throw new BizException("未登陆", ExceptionType.NOT_LOGIN);
            }

            Client client = clientDao.findBo(Long.parseLong(clientIdInRedis));

            if (client == null) {
                log.error("未登陆");
                throw new BizException("未登陆", ExceptionType.NOT_LOGIN);
            }

            long end = System.currentTimeMillis();
            log.info("微信路由完毕，费时：" + (end - start));
        }

        return true;
    }


    private boolean isApp(String requestPath) {
        return requestPath.indexOf("/rest/") == 0;
    }
}
