package com.xth.service;

/**
 * @author admin
 */

public enum FileSource {

    /**
     * 上传
     */
    ATTACHMENT("上传"),
    /**
     * 报表
     */
    REPORT("报表"),
    /**
     * 导出
     */
    EXPORT("导出");

    private String name;

    FileSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
