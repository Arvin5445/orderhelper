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

    public static <T> void order(List<T> list, Function<T, Comparable>... function) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable> f : function) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(f);
            orderConfigs.add(orderConfig);
        }
        order(list, orderConfigs);
    }

    public static <T> void orderDesc(List<T> list, Function<T, Comparable>... function) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable> f : function) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(f);
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

    public static <T> void order(List<T> list, List<OrderConfig<T>> orderConfigs) {
        if (list == null || list.size() < 2) {
            return;
        }
        OrderHelper<T> orderhelper = new OrderHelper<>(orderConfigs);
        list.sort(orderhelper);
    }

    public static <T> void order(List<T> list) {
        throw new OrderException("未设置 function");
    }

    public static <T> void orderDesc(List<T> list) {
        throw new OrderException("未设置 function");
    }
}
