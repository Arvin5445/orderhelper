/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtilsv
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * 排序工具类
 */
public class OrderUtils {

    /**
     * 升序排序
     *
     * @param list      待排序集合
     * @param fieldName 排序依据的字段
     */
    public static void order(List list, String fieldName) {
        OrderConfig orderConfig = new OrderConfig();
        orderConfig.setList(list);
        orderConfig.setFieldName(fieldName);
        orderExcute(orderConfig);
    }

    /**
     * 降序排序
     *
     * @param list      待排序集合
     * @param fieldName 排序依据的字段
     */
    public static void orderDesc(List list, String fieldName) {
        OrderConfig orderConfig = new OrderConfig();
        orderConfig.setList(list);
        orderConfig.setFieldName(fieldName);
        orderConfig.setOrderMode(1);
        orderExcute(orderConfig);
    }

    /**
     * 升序排序
     *
     * @param list      待排序集合
     * @param fieldName 排序依据的字段
     * @param mapper    自定义映射器
     */
    public static void order(List list, String fieldName, Function<Object, Comparable> mapper) {
        OrderConfig orderConfig = new OrderConfig();
        orderConfig.setList(list);
        orderConfig.setFieldName(fieldName);
        orderConfig.setMapper(mapper);
        orderExcute(orderConfig);
    }

    /**
     * 降序排序
     *
     * @param list      待排序集合
     * @param fieldName 排序依据的字段
     * @param mapper    自定义映射器
     */
    public static void orderDesc(List list, String fieldName, Function<Object, Comparable> mapper) {
        OrderConfig orderConfig = new OrderConfig();
        orderConfig.setList(list);
        orderConfig.setFieldName(fieldName);
        orderConfig.setMapper(mapper);
        orderConfig.setOrderMode(1);
        orderExcute(orderConfig);
    }

    public static void orderExcute(OrderConfig orderConfig) {
        List list = orderConfig.getList();
        if (list == null || list.size() < 2) {
            return;
        }
        Comparator orderhelper = new OrderHelper(orderConfig);
        if (orderConfig.getOrderMode() == 1) {
            orderhelper = orderhelper.reversed();
        }
        list.sort(orderhelper);
    }

}
