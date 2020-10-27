/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtilsv
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 排序工具类
 */
public class OrderUtils {

    /**
     * 升序排序
     *
     * @param list  待排序集合
     * @param param 排序依据的字段
     */
    public static void order(List list, String param) {
        order(list, param, null);
    }

    /**
     * 降序排序
     *
     * @param list  待排序集合
     * @param param 排序依据的字段
     */
    public static void orderDesc(List list, String param) {
        orderDesc(list, param, null);
    }

    /**
     * 升序排序
     *
     * @param list   待排序集合
     * @param param  排序依据的字段
     * @param mapper 自定义映射器
     */
    public static void order(List list, String param, Function<Object, Comparable> mapper) {
        if (list == null || list.size() < 2) {
            return;
        }
        Orderhelper orderhelper = new Orderhelper(param, list.get(0).getClass());
        orderhelper.setMapper(mapper);
        list.sort(orderhelper);
    }

    /**
     * 降序排序
     *
     * @param list   待排序集合
     * @param param  排序依据的字段
     * @param mapper 自定义映射器
     */
    public static void orderDesc(List list, String param, Function<Object, Comparable> mapper) {
        if (list == null || list.size() < 2) {
            return;
        }
        Orderhelper orderhelper = new Orderhelper(param, list.get(0).getClass());
        Set<Object> mins = new HashSet<>();
        Set<Object> maxs = new HashSet<>();
        mins.add("--");
        maxs.add("MAX");
        orderhelper.setMaxOneValue(maxs);
        orderhelper.setMinOneValue(mins);
        orderhelper.setMapper(mapper);
        list.sort(orderhelper.reversed());
    }

}
