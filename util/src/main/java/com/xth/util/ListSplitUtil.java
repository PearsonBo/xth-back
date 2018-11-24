package com.xth.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:39
 */
public class ListSplitUtil {

    /**
     * 默认拆分的集合大小
     */
    public static final int DEFAULT_SPLIT_SIZE = 999;

    public static <T> List<List<T>> splitList(List<T> list) {
        return splitList(list, DEFAULT_SPLIT_SIZE);
    }

    public static <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> ret = new ArrayList<>();
        int totalLength = list.size();
        while (totalLength > 0) {
            List<T> listInner;
            if (totalLength - getSplitSize(size) > 0) {
                listInner = list.subList(totalLength - getSplitSize(size), totalLength);
            } else {
                listInner = list.subList(0, totalLength);
            }
            ret.add(listInner);
            totalLength = totalLength - listInner.size();
        }
        return ret;
    }

    private static int getSplitSize(int size) {
        return size > 0 ? size : DEFAULT_SPLIT_SIZE;
    }
}
