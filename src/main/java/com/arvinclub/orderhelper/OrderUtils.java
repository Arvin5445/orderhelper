/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtils
 * Author: 01397386
 * Date: 2020/10/26 14:00
 * Description:
 */
package com.arvinclub.orderhelper;


import java.util.List;

public class OrderUtils {

    public static void order(List list, String param) {
        list.sort((o1, o2) -> {
            OrderBase ob1 = (OrderBase) o1;
            OrderBase ob2 = (OrderBase) o2;
            ob1.setOrderParam(param);
            ob2.setOrderParam(param);
            return ob1.compareTo(ob2);
        });
    }

    public static void orderDesc(List list, String param) {
        list.sort((o1, o2) -> {
            OrderBase ob1 = (OrderBase) o1;
            OrderBase ob2 = (OrderBase) o2;
            ob1.setOrderParam(param);
            ob2.setOrderParam(param);
            return ob2.compareTo(ob1);
        });
    }
}
