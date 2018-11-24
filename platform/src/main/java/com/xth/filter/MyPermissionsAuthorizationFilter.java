package com.xth.filter;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author
 * @date 2018/2/23
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    private final static String PERMISSION_DENIED_URL = "/web/user/permissionDenied";

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, PERMISSION_DENIED_URL);
        return false;
    }

}
