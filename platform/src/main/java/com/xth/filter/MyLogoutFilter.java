package com.xth.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author
 * @date 2018/2/27
 */
public class MyLogoutFilter extends LogoutFilter {

    private static final Logger LOG = LoggerFactory.getLogger(MyLogoutFilter.class);

    /**
     * Acquires the currently executing {@link #getSubject(ServletRequest, ServletResponse) subject},
     * a potentially Subject or request-specific
     * {@link #getRedirectUrl(ServletRequest, ServletResponse, Subject) redirectUrl},
     * and redirects the end-user to that redirect url.
     *
     * @param request  the incoming ServletRequest
     * @param response the outgoing ServletResponse
     * @return {@code false} always as typically no further interaction should be done after user logout.
     * @throws Exception if there is any error.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            LOG.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        return false;
    }


}
