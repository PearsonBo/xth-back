package com.xth.service;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/16 0016 下午 21:06
 */
public class ImportExcelUtil {

    /**
     * 拼装单个obj
     *
     * @param obj
     * @param row
     * @return
     * @throws Exception
     */
    private static Map<String, Object> dataObj(Object obj, Row row) throws Exception {
        Class<?> rowClazz = obj.getClass();
        Field[] fields = FieldUtils.getAllFields(rowClazz);
        if (fields == null || fields.length < 1) {
            return null;
        }

        //容器
        Map<String, Object> map = new HashMap<>();

        //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
        for (int j = 0; j < fields.length; j++) {
            map.put(fields[j].getName(), getVal(row.getCell(j)));
        }
        return map;
    }

    public static List<Map<String, Object>> importExcel(MultipartFile file, Object obj) throws Exception {

        //装载流
        Workbook hw = new XSSFWorkbook(file.getInputStream());


        //获取第一个sheet页
        Sheet sheet = hw.getSheetAt(0);

        //容器
        List<Map<String, Object>> ret = new ArrayList<>();

        //遍历行 从下标第一行开始（去除标题）
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                //装载obj
                ret.add(dataObj(obj, row));
            }
        }
        return ret;
    }

    /**
     * 处理val（暂时只处理string和number，可以自己添加自己需要的val类型）
     *
     * @param hssfCell
     * @return
     */
    public static Object getVal(Cell cell) {
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case HSSFCell.CELL_TYPE_FORMULA:
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
        return "";
    }
}