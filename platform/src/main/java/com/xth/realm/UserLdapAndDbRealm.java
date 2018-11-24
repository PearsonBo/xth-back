package com.xth.realm;

import com.xth.helper.SecurityRedisHelper;
import com.xth.model.vo.operator.OperatorVo;
import com.xth.service.OperationService;
import com.xth.service.operator.helper.OperatorServiceHelper;
import com.xth.util.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author
 */
@Slf4j
public class UserLdapAndDbRealm extends AuthorizingRealm {

    private static final String REALM_NAME = "userLdapAndDbRealm";

    @Autowired
    private OperatorServiceHelper operatorServiceHelper;

    @Autowired
    private SecurityRedisHelper securityRedisHelper;

    @Override
    public String getName() {
        return REALM_NAME;
    }

    /**
     * 获取用户信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始调用UserLdapAndDbRealm");
        return getAccountAuthenticationInfo(token);
    }

    /**
     * 获取用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || subject.getPrincipal() == null) {
            throw new AuthorizationException("exc_user_user_not_existed");
        }

        String sessionId = subject.getSession().getId().toString();

        OperatorVo operator = securityRedisHelper.getOperator(sessionId);
        Set<String> roles = securityRedisHelper.getRoles(sessionId, operator.getId());
        Set<String> perms = securityRedisHelper.getPerms(sessionId, operator.getId());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;

    }

    private AuthenticationInfo getAccountAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始用户验证");
        String operatorName = (String) token.getPrincipal();

        if (StringUtils.isEmpty(operatorName)) {
            throw new IncorrectCredentialsException();
        }

        // 根据用户名从数据库查询用户
        OperatorVo operator = operatorServiceHelper.findVoByLoginName(operatorName);

        if (operator == null) {
            throw new IncorrectCredentialsException("系统中不存在该用户，请联系管理员维护");
        }

        try {
            operator.setPassword(AesUtils.aesDecryptHexString(operator.getPassword(), "xth123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 交给 CredentialMatcher 进行密码匹配
        return new SimpleAuthenticationInfo(operator.getLoginName(), operator, REALM_NAME);
    }

}
