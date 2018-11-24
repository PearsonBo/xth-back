package com.xth.helper;

import com.xth.dao.operator.OperatorDao;
import com.xth.model.vo.operator.OperatorVo;
import com.xth.service.OperationService;
import com.xth.service.RedisReadHelper;
import com.xth.service.operator.helper.OperatorServiceHelper;
import com.xth.util.AuthorizationUtil;
import com.xth.util.JsonMapper;
import com.xth.util.JsonMapperFactory;
import com.xth.util.SecurityKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @date 2018/5/4
 */
@Service
public class SecurityRedisHelper {

    @Autowired
    private OperatorServiceHelper operatorServiceHelper;

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private RedisReadHelper redisReadHelper;

    protected final JsonMapper jsonMapper = JsonMapperFactory.getJsonMapper();

    public void deleteOperator(String sessionId) {
        String key = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        redisReadHelper.delete(key);
    }

    public OperatorVo getOperator(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return getOperator((HttpServletRequest) request);
        }
        return null;
    }

    public OperatorVo getOperator(HttpServletRequest request) {
        String sessionId = AuthorizationUtil.getSessionId(request);
        if (sessionId == null) {
            return null;
        }
        return getOperator(sessionId);
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }
        String rolesKey = SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, operatorId);
        String rolesJson = redisReadHelper.getStringInRedis(rolesKey);
        return (Set<String>) jsonMapper.fromJson(rolesJson, Set.class);

    }

    @SuppressWarnings("unchecked")
    public Set<String> getPerms(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }
        String permsKey = SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, operatorId);
        String permsJson = redisReadHelper.getStringInRedis(permsKey);
        return (Set<String>) jsonMapper.fromJson(permsJson, Set.class);
    }

    public OperatorVo getOperator(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        String key = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String operatorJson = redisReadHelper.getStringInRedis(key);
        return jsonMapper.fromJson(operatorJson, OperatorVo.class);
    }

    public void saveOperator(String sessionId, String loginName) {
        if (sessionId == null || loginName == null) {
            return;
        }

        OperatorVo operator = operatorServiceHelper.findVoByLoginName(loginName);

        saveOperatorJson(sessionId, operator);

        saveRolesJson(sessionId, operator);

        savePermsJson(sessionId, operator);

    }

    private void savePermsJson(String sessionId, OperatorVo operator) {
        String permsKey = SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, operator.getId());
        Set<String> perms = operatorServiceHelper.listPermissionList(operator.getId());

        String permsJson = jsonMapper.toJson(perms);
        redisReadHelper.saveStringInRedis(permsKey, permsJson);
    }

    private void saveRolesJson(String sessionId, OperatorVo operator) {
        String rolesKey = SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, operator.getId());
        Set<String> roles = operatorServiceHelper.listRoleList(operator.getId());

        String rolesJson = jsonMapper.toJson(roles);
        redisReadHelper.saveStringInRedis(rolesKey, rolesJson);
    }

    private void saveOperatorJson(String sessionId, OperatorVo operator) {
        String operatorKey = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String operatorJson = jsonMapper.toJson(operator);
        redisReadHelper.saveStringInRedis(operatorKey, operatorJson);
    }

}
