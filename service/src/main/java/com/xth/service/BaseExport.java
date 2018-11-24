package com.xth.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 21:54
 */
public abstract class BaseExport<T> extends AbstractTransactionalService {

    private static final long ONE_WEEK = 1000 * 60 * 60 * 24 * 7L;

    private static final String XLS = ".xlsx";

    @Autowired
    private FileStorageHelper fileStorageHelper;

    /**
     * 需要实现的导出方法
     */
    public String export(T so) throws Exception {
        return null;
    }

    /**
     * 删除同名历史导出文件
     *
     * @param storeName 导出文件名
     */
    private void cleanExportFile(String storeName) {
        File storeDir = fileStorageHelper.getExportDir();
        if (!storeDir.isDirectory()) {
            return;
        }
        File[] files = storeDir.listFiles();
        if (files == null) {
            return;
        }
        Path path;
        for (File elm : files) {
            String fileName = elm.getName();
            if (fileName.startsWith(storeName)
                    && (System.currentTimeMillis() - elm.lastModified()) > ONE_WEEK) {
                path = Paths.get(elm.getPath());
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.error("删除{}文件错误", fileName, e);
                }
            }
        }
    }

    /**
     * 根据时间戳生成导出文件名
     */
    private String geneFileName(String storeName) {
        storeName = storeName + "_" + System.currentTimeMillis() + XLS;
        return storeName;
    }
}
