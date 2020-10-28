/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtilsv
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 花式排序工具类
 */
public class OrderUtils {

    public static <T> void order(List<T> list, Function<T, Comparable> function) {
        OrderConfig<T> orderConfigs = new OrderConfig<>();
        orderConfigs.setMapper(function);
        order(list, orderConfigs);
    }

    //    /**
//     * 升序排序
//     *
//     * @param list      待排序集合
//     * @param fieldName 排序依据的字段
//     */
//    public static void order(List list, String fieldName) {
//        OrderConfig orderConfig = new OrderConfig();
//        orderConfig.setFieldName(fieldName);
//        order(list, orderConfig);
//    }
//
//    /**
//     * 降序排序
//     *
//     * @param list      待排序集合
//     * @param fieldName 排序依据的字段
//     */
//    public static void orderDesc(List list, String fieldName) {
//        OrderConfig orderConfig = new OrderConfig();
//        orderConfig.setFieldName(fieldName);
//        orderConfig.setOrderMode(OrderConfig.DESC_MODE);
//        order(list, orderConfig);
//    }
//
//    /**
//     * 升序排序
//     *
//     * @param list      待排序集合
//     * @param fieldName 排序依据的字段
//     * @param mapper    自定义映射器
//     */
//    public static void order(List list, String fieldName, Function<Object, Comparable> mapper) {
//        OrderConfig orderConfig = new OrderConfig();
//        orderConfig.setFieldName(fieldName);
//        orderConfig.setMapper(mapper);
//        order(list, orderConfig);
//    }
//
//    /**
//     * 降序排序
//     *
//     * @param list      待排序集合
//     * @param fieldName 排序依据的字段
//     * @param mapper    自定义映射器
//     */
//    public static void orderDesc(List list, String fieldName, Function<Object, Comparable> mapper) {
//        OrderConfig orderConfig = new OrderConfig();
//        orderConfig.setFieldName(fieldName);
//        orderConfig.setMapper(mapper);
//        orderConfig.setOrderMode(OrderConfig.DESC_MODE);
//        order(list, orderConfig);
//    }
//
//
    public static <T> void order(List<T> list, OrderConfig<T> orderConfig) {
        order(list, new ArrayList<OrderConfig<T>>(1) {{
            add(orderConfig);
        }});
    }

    public static <T> void order(List<T> list, List<OrderConfig<T>> orderConfigs) {
        if (list == null || list.size() < 2) {
            return;
        }
        OrderHelper<T> orderhelper = new OrderHelper<>(orderConfigs);
        list.sort(orderhelper);
    }
}
