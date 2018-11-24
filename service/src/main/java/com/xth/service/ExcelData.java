package com.xth.service;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/11 0011 下午 21:06
 */
@Data
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

    private List<String> titles;

    private List<List<Object>> rows;

    private String name;

}