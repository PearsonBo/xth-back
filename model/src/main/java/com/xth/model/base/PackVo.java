package com.xth.model.base;

import com.xth.model.enums.PredefineCode;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 22:09
 */
@Data
public class PackVo<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -3134094617983454403L;

    /**
     * 调用是否成功
     */
    private Boolean success = Boolean.TRUE;

    /**
     * code
     */
    private String code;

    /**
     * 错误及提示
     */
    private String message;

    /**
     * 错误及提示
     */
    private List<String> messages;

    /**
     * 返回对象
     */
    private T vo;

    /**
     * 返回PageList
     */
    private PageList<T> pageList;

    /**
     * 返回列表
     */
    private List<T> voList;

    /**
     * 返回列表长度，数值
     */
    private Long udf1;

    /**
     * 返回值
     */
    private String udf2;

    /**
     * 返回值
     */
    private String udf3;

    /**
     * 返回值
     */
    private String udf4;

    /**
     * 返回值
     */
    private String udf5;

    /**
     * 返回值
     */
    private String udf6;

    private List<T> udf7;

    private Object udf8;

    /**
     * 是否需要确认框
     */
    private Boolean confirm;

    public Boolean getSuccess() {
        return success;
    }

    public List<T> getUdf7() {
        return udf7;
    }

    public void setUdf7(List<T> udf7) {
        this.udf7 = udf7;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getVo() {
        return vo;
    }

    public void setVo(T vo) {
        this.vo = vo;
    }

    public List<T> getVoList() {
        return voList;
    }

    public void setVoList(List<T> voList) {
        this.voList = voList;
    }

    public Long getUdf1() {
        return udf1;
    }

    public void setUdf1(Long udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public String getUdf6() {
        return udf6;
    }

    public void setUdf6(String udf6) {
        this.udf6 = udf6;
    }

    public PageList<T> getPageList() {
        return pageList;
    }

    public void setPageList(PageList<T> pageList) {
        this.pageList = pageList;
    }

    public Object getUdf8() {
        return udf8;
    }

    public void setUdf8(Object udf8) {
        this.udf8 = udf8;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }
}