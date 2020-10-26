/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderUtils
 * Author: 01397386
 * Date: 2020/10/26 14:00
 * Description:
 */
package com.arvinclub.orderhelper;

import java.lang.reflect.Method;
import java.util.function.Function;

public class OrderBase implements Comparable<OrderBase> {

    // 字段名
    private String orderParamName;
    // 获取字段的 get 方法
    private Method orderParamMethod;
    // 是否检查完成
    private boolean checked;
    private Function<Object, Comparable> mode;

    @Override
    public int compareTo(OrderBase o) {
        preCheck(o);
        return getOrderParam().compareTo(o.getOrderParam());
    }

    public void setOrderParam(String orderParam) {
        this.orderParamName = orderParam;
    }

    private Comparable getOrderParam() {
        try {
            return (Comparable) orderParamMethod.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void preCheck() {
        try {
            orderParamMethod = this.getClass().getMethod("get" + orderParamName.substring(0, 1).toUpperCase() + orderParamName.substring(1));
        } catch (Exception e) {
            throw new RuntimeException("获取对象: " + this + " 中的字段: " + orderParamName + " 失败");
        }
        checked = true;
    }

    private void preCheck(OrderBase orderBase) {
        if (checked && orderBase.checked) {
            return;
        }
        if ((!checked) && (!orderBase.checked)) {
            preCheck();
            orderBase.orderParamMethod = orderParamMethod;
        } else if (checked) {
            orderBase.orderParamMethod = orderParamMethod;
        } else {
            orderParamMethod = orderBase.orderParamMethod;
        }
        checked = true;
        orderBase.checked = true;
    }


}
