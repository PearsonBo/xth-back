package com.xth.controller;

import com.xth.helper.SecurityRedisHelper;
import com.xth.model.base.PackVo;
import com.xth.model.vo.operator.OperatorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author Hu Jianbo
 * @date: 2018/7/24
 */
@RestController
@RequestMapping(WebUserController.VIEW_PREFIX)
@Api(tags = {"登录接口"})
public class WebUserController {
    public static final String VIEW_PREFIX = "web/user";
    private static final String GET_AUTHORIZATION = "/getAuthorization";
    private static final String AJAX_LOGIN = "/ajaxLogin";
    private static final String PERMISSION_DENIED = "/permissionDenied";
    private static final String UN_AUTH = "/unAuth";
    private static final String LOGOUT = "/logout";

    protected static final Logger LOG = LoggerFactory.getLogger(WebUserController.class);
    private static final String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";

    @Autowired
    private SecurityRedisHelper redisReadHelper;

    /**
     * 第一步、获取sessionId
     */
    @ApiOperation(value = "第一步、获取sessionId", notes = "vo返回Authorization，之后请求头带上Authorization")
    @RequestMapping(value = GET_AUTHORIZATION, method = RequestMethod.GET)
    public PackVo<Serializable> getAuthorization(HttpServletRequest req) {
        PackVo<Serializable> packVo = new PackVo<>();
        packVo.setVo(req.getSession().getId());
        return packVo;
    }

    /**
     * 第二步、登陆
     */
    @ApiOperation(value = "第二步、登陆", notes = "表单提交，参数demo：loginName:admin, password:111111")
    @RequestMapping(value = AJAX_LOGIN, method = RequestMethod.POST)
    public PackVo<Serializable> ajaxLogin(HttpServletRequest req, @RequestBody OperatorVo operator) {
        PackVo<Serializable> packVo = new PackVo<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(operator.getLoginName(), operator.getPassword());
        token.setRememberMe(operator.getRememberMe() == null ? false : operator.getRememberMe());
        try {
            LOG.debug("登录前清除原session");
            subject.getSession().stop();
            redisReadHelper.deleteOperator(subject.getSession().getId().toString());
            LOG.debug("登陆");
            subject.login(token);
            LOG.debug("登录成功后复制session数据");
            String sessionId = subject.getSession().getId().toString();
            packVo.setVo(sessionId);
            redisReadHelper.saveOperator(sessionId, operator.getLoginName());
        } catch (IncorrectCredentialsException e) {
            packVo.setSuccess(false);
            packVo.setMessage("用户名或密码错误");
        } catch (LockedAccountException e) {
            packVo.setSuccess(false);
            packVo.setMessage("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            packVo.setSuccess(false);
            packVo.setMessage("该用户不存在");
        } catch (Exception e) {
            LOG.error("登录发生错误, {}", e);
        }
        return packVo;
    }

    @ApiOperation(value = "登出", notes = "")
    @RequestMapping(value = LOGOUT, method = RequestMethod.GET)
    public PackVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().stop();
        redisReadHelper.deleteOperator(subject.getSession().getId().toString());
        PackVo<String> packVo = new PackVo<>();
        packVo.setSuccess(true);
        packVo.setMessage("完成登出");
        return packVo;
    }

    /**
     * 对应LoginUrl
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     */
    @ApiIgnore
    @RequestMapping(value = PERMISSION_DENIED, method = RequestMethod.GET)
    public PackVo permissionDenied() {
        PackVo<String> packVo = new PackVo<>();
        packVo.setSuccess(false);
        packVo.setMessage("权限验证未通过，不能执行该操作");
        return packVo;
    }

    /**
     * 对应LoginUrl
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     */
    @RequestMapping(value = UN_AUTH, method = RequestMethod.GET)
    @ApiIgnore
    public PackVo unAuth(HttpServletRequest req, HttpServletResponse response) {
        PackVo<String> packVo = new PackVo<>();
        packVo.setSuccess(false);
        packVo.setMessage(getShiroLoginFailure(req));
        packVo.setCode("403");
        response.setStatus(403);
        return packVo;
    }

    /**
     * 获取shiro提示的登陆异常信息
     *
     * @param req 请求
     * @return shiro登陆异常信息
     */
    private String getShiroLoginFailure(HttpServletRequest req) {
        String error = null;
        String exceptionClassName = (String) req.getAttribute(SHIRO_LOGIN_FAILURE);

        if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "该用户已锁定";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误次数过多，请30分钟后再登录";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }

        return error;
    }
}
