package com.xth.util;

/**
 * @author
 * @date 2018/5/4
 */
public class SecurityKeyUtils {

    /**
     * 生成用户对象存redis的key
     *
     * @param sessionId sessionId
     * @return 用户
     */
    public static String geneSessionOperatorKey(String sessionId) {
        if (sessionId == null) {
            return null;
        }

        return "operator:" + sessionId;
    }

    /**
     * 生成角色对象存redis的key
     *
     * @param sessionId  sessionId
     * @param operatorId 用户Id
     * @return 用户
     */
    public static String geneSessionOperatorRolesKey(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }

        return "roles:" + sessionId + "-" + operatorId;
    }

    /**
     * 生成权限对象存redis的key
     *
     * @param sessionId  sessionId
     * @param operatorId 用户Id
     * @return 用户
     */
    public static String geneSessionOperatorPermsKey(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }

        return "perms:" + sessionId + "-" + operatorId;
    }

}
