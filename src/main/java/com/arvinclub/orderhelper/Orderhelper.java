/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Orderhelper
 * Author: 01397386
 * Date: 2020/10/27 10:52
 * Description:
 */
package com.arvinclub.orderhelper;


import java.lang.reflect.Method;
import java.util.Comparator;

public class Orderhelper implements Comparator {

    // 字段名
    private String orderParamName;
    // 获取字段的 get 方法
    private Method orderParamMethod;

    private Class clazz;


    public Orderhelper(String orderParamName, Class clazz) {
        this.orderParamName = orderParamName;
        this.clazz = clazz;
        preCheck();
    }

    public Comparable getOrderParamValue(Object enitty) {
        try {
            return (Comparable) orderParamMethod.invoke(enitty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void preCheck() {
        try {
            orderParamMethod = clazz.getMethod("get" + orderParamName.substring(0, 1).toUpperCase() + orderParamName.substring(1));
        } catch (Exception e) {
            throw new RuntimeException("获取类型: " + clazz + " 中的字段: " + orderParamName + " 失败");
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        return getOrderParamValue(o1).compareTo(getOrderParamValue(o2));

    }
}
