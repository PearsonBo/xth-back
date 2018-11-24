package com.xth.config;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 22:48
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> THREAD_LOCAL = new ThreadLocal<>();

    public static DatabaseType getDatabaseType() {
        return THREAD_LOCAL.get();
    }

    public static void setDatabaseType(DatabaseType type) {
        THREAD_LOCAL.set(type);
    }
}
