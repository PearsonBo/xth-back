package com.xth.service;

import com.xth.model.constant.CommonConstant;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/10 0011 下午 21:54
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "file.download")
@Data
public class FileStorageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(FileStorageHelper.class);

    private String host;

    private String url;

    private String path;

    private static final String REPORT_DIR = "report";

    private static final String EXPORT_DIR = "export";

    private static final String ATTACHMENT_DIR = "attachment";

    public File createReportFile(String fileName) {
        File storageDir = new File(getPath() + getSubDir4Report());
        return new File(storageDir, fileName);
    }

    public File getExportDir() {
        return new File(getPath() + getSubDir4Export());
    }

    public File createExportFile(String fileName) {
        File storageDir = new File(getPath() + getSubDir4Export());
        return new File(storageDir, fileName);
    }

    public String getDownloadDirectory(FileSource fileSource, String fileName) {
        String resultUrl = null;
        switch (fileSource) {
            case ATTACHMENT:
                resultUrl = getUrl() + getSubDir4Attachment() + fileName;
                break;
            case REPORT:
                resultUrl = getUrl() + getSubDir4Report() + fileName;
                break;
            case EXPORT:
                resultUrl = getUrl() + getSubDir4Export() + fileName;
                break;
            default:
                break;
        }
        return resultUrl;
    }

    private static String getSubDir4Attachment() {
        String monthStr = DateFormatUtils.format(Calendar.getInstance(), "yyyyMM");
        return CommonConstant.SLASH + ATTACHMENT_DIR + CommonConstant.SLASH + monthStr + CommonConstant.SLASH;
    }

    private static String getSubDir4Report() {
        String monthStr = DateFormatUtils.format(Calendar.getInstance(), "yyyyMM");
        return CommonConstant.SLASH + REPORT_DIR + CommonConstant.SLASH + monthStr + CommonConstant.SLASH;
    }

    private static String getSubDir4Export() {
        return CommonConstant.SLASH + EXPORT_DIR + CommonConstant.SLASH;
    }

    public String getDownloadHost() {
        return getHost();
    }

}
