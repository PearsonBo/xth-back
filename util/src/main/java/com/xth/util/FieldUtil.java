package com.xth.util;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 22:05
 */
public class FieldUtil {

    private FieldUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String SET = "set";

    private static final Logger LOG = LoggerFactory.getLogger(FieldUtil.class);

    public static List<String> getFieldNameList(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNameList = new ArrayList<>();
        for (Field field : fields) {
            fieldNameList.add(field.getName());
        }
        return fieldNameList;
    }

    /**
     * 获取当前类和父类的字段
     */
    @SuppressWarnings("rawtypes")
    public static List<Field> getParentAndSelfFields(Class clazz) {

        Field[] parentFields = getParentFields(clazz);
        Field[] fields = getFields(clazz);

        List<Field> all = Arrays.asList(parentFields);
        List<Field> temp = new ArrayList<>(all);
        for (Field f : fields) {
            boolean flag = true;
            for (int i = 0; i < parentFields.length; i++) {
                if (parentFields[i].getName().equalsIgnoreCase(f.getName())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                temp.add(f);
            }
        }

        return temp;
    }

    /**
     * 获取当前类的字段
     */
    @SuppressWarnings("rawtypes")
    public static List<Field> getSelfFields(Class clazz) {

        return Arrays.asList(getFields(clazz));
    }

    /**
     * 得到父类的字段
     */
    @SuppressWarnings("rawtypes")
    private static Field[] getParentFields(Class clazz) {
        Class sClass = clazz.getSuperclass();
        List<Field> temp = new ArrayList<>();
        while (sClass != null) {
            Field[] superfields = sClass.getDeclaredFields();
            //就是为了mapp好看
            CollectionUtils.reverseArray(superfields);
            for (Field f : superfields) {
                if ("serialVersionUID".equalsIgnoreCase(f.getName())) {
                    continue;
                }
                temp.add(f);
            }
            sClass = hasParent(sClass);
        }
        Field[] ret = temp.toArray(new Field[temp.size()]);
        //就是为了mapp好看
        CollectionUtils.reverseArray(ret);
        return ret;
    }

    /**
     * 得到类字段
     */
    @SuppressWarnings("rawtypes")
    private static Field[] getFields(Class clazz) {

        Field[] fields = clazz.getDeclaredFields();
        List<Field> temp = new ArrayList<>();
        for (Field f : fields) {
            if ("serialVersionUID".equalsIgnoreCase(f.getName())) {
                continue;
            }

            temp.add(f);
        }
        return temp.toArray(new Field[temp.size()]);
    }

    @SuppressWarnings("rawtypes")
    private static Class hasParent(Class clazz) {
        Class sclass = clazz.getSuperclass();
        if (sclass != null && sclass.getSuperclass() != null) {
            return sclass;
        }
        return null;
    }

    /**
     * 判断一个类是否为基本数据类型。
     *
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    public static boolean isBaseDataType(@SuppressWarnings("rawtypes") Class clazz) {
        return (
                clazz.equals(String.class) ||
                        clazz.equals(Integer.class) ||
                        clazz.equals(Byte.class) ||
                        clazz.equals(Long.class) ||
                        clazz.equals(Double.class) ||
                        clazz.equals(Float.class) ||
                        clazz.isEnum() ||
                        clazz.equals(Character.class) ||
                        clazz.equals(Short.class) ||
                        clazz.equals(BigDecimal.class) ||
                        clazz.equals(BigInteger.class) ||
                        clazz.equals(Boolean.class) ||
                        clazz.equals(Date.class) ||
                        clazz.equals(List.class) ||
                        clazz.isPrimitive()
        );
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public static String getTypesByField(Field field) {
        String res = "VARCHAR";
        Class type = field.getType();
        if (type == String.class) {
            res = "VARCHAR";
        } else if (type == Integer.class || type == Long.class || type == int.class
                || type == long.class || type == Double.class
                || type == double.class || type == Float.class || type == float.class
                || type == BigDecimal.class) {
            res = "NUMERIC";
        } else if (type == Date.class) {
            res = "TIMESTAMP";
        } else if (type == Boolean.class || type == boolean.class) {
            res = "BIT";
        }
        return res;
    }

    /**
     * 将对象中异常的日期转为普通的日期
     */
    @SuppressWarnings("unchecked")
    public static void formatDate(Object bo) {
        Method[] methodsArray = bo.getClass().getMethods();
        for (Method method : methodsArray) {
            String methodName = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            if (methodName.startsWith(SET)
                    && parameterTypes != null
                    && parameterTypes.length > 0
                    && parameterTypes[0].isAssignableFrom(Date.class)) {
                try {
                    method.invoke(bo, new Date());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOG.error("异常", e);
                }
            } else if (methodName.startsWith(SET)
                    && parameterTypes != null
                    && parameterTypes.length > 0
                    && parameterTypes[0].isAssignableFrom(Boolean.class)) {
                try {
                    method.invoke(bo, true);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOG.error("异常", e);
                }
            }
        }
    }

}
