package com.xth.realm;

import com.xth.model.vo.operator.OperatorVo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author
 */
public class LdapAndDbCredentialMatcher extends HashedCredentialsMatcher {

    private static final Logger LOG = LoggerFactory.getLogger(LdapAndDbCredentialMatcher.class);

    /**
     * 检查密码
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return doCredentials4AuthenticationToken(token, info);
    }

    private boolean doCredentials4AuthenticationToken(AuthenticationToken token, AuthenticationInfo info) {
        String loginName = (String) token.getPrincipal();
        String password = token.getCredentials() == null ? null : String.valueOf((char[]) token.getCredentials());
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            throw new IncorrectCredentialsException();
        }

        LOG.debug("Credentials 以 Object 形式传递");
        OperatorVo operator = (OperatorVo) info.getCredentials();

        if (checkPassword(loginName, password, operator)) {
            // 刷新最近登录时间、最近尝试登陆时间、清除密码错误次数
            LOG.info("密码验证成功");
            return true;
        } else {
            // 使用错误的密码登陆，更新最后尝试登陆时间，增加密码重试次数
            LOG.info("密码验证失败");
            return false;
        }
    }

    private boolean checkPassword(String loginName, String password, OperatorVo operator) {
        return loginName.equals(operator.getLoginName()) && password.equals(operator.getPassword());
    }

}
