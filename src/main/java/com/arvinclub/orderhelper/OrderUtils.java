/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtilsv
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper;


import java.util.List;

public class OrderUtils {

    public static void order(List list, String param) {
        if (list == null || list.size() < 2) {
            return;
        }
        list.sort(new Orderhelper(param, list.get(0).getClass()));
    }

    public static void orderDesc(List list, String param) {
        if (list == null || list.size() < 2) {
            return;
        }
        list.sort(new Orderhelper(param, list.get(0).getClass()).reversed());
    }

}
