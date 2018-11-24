package com.xth.controller.login;

import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/19 0019 下午 21:27
 */
@RestController
@RequestMapping(LoginController.VIEW_PREFIX)
public class LoginController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/login";

    private static final String ON_LOGIN = "/onLogin";

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = ON_LOGIN, method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(dataType = "String", name = "code", value = "用户登录凭证", required = true)})
    public PackVo onLogin(@RequestParam String code) {

        PackVo<String> packVo = new PackVo<>();
        String sessionId = loginService.login(code);
        packVo.setVo(sessionId);

        return packVo;
    }

}
