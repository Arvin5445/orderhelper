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

    /**
     * 升序排序
     *
     * @param list    待排序集合
     * @param mappers 通过实体获取字段值的映射器
     * @param <T>     实体类型
     */
    @SafeVarargs
    public static <T> void order(List<T> list, Function<T, Comparable<?>>... mappers) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable<?>> mapper : mappers) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(mapper);
            orderConfigs.add(orderConfig);
        }
        order(list, orderConfigs);
    }

    /**
     * 降序排序
     *
     * @param list    待排序集合
     * @param mappers 通过实体获取字段值的映射器
     * @param <T>     实体类型
     */
    @SafeVarargs
    public static <T> void orderDesc(List<T> list, Function<T, Comparable<?>>... mappers) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable<?>> mapper : mappers) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(mapper);
            orderConfig.setOrderMode(OrderConfig.DESC_MODE);
            orderConfigs.add(orderConfig);
        }
        order(list, orderConfigs);
    }

    public static <T> void order(List<T> list, OrderConfig<T> orderConfig) {
        order(list, new ArrayList<OrderConfig<T>>(1) {{
            add(orderConfig);
        }});
    }

    /**
     * 实际调用此方法
     */
    public static <T> void order(List<T> list, List<OrderConfig<T>> orderConfigs) {
        if (list == null || list.size() < 2) {
            return;
        }
        OrderHelper<T> orderhelper = new OrderHelper<>(orderConfigs);
        // 真正开始排序
        list.sort(orderhelper);
    }

    /**
     * 抛出异常
     */
    public static <T> void order(List<T> list) {
        throw new OrderException("未设置 function");
    }

    public static <T> void orderDesc(List<T> list) {
        throw new OrderException("未设置 function");
    }
}
