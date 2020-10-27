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


/**
 * 辅助比较器
 */
public class OrderHelper implements Comparator {

    private OrderConfig orderConfig;
    private static final Comparable MAX_VALUE = o -> 1;
    private static final Comparable MIN_VALUE = o -> -1;
    // 元素类型
    private Class entityClass;
    // 获取字段的 Get 方法
    private Method fieldMethod;

    public OrderHelper() {
    }

    public OrderHelper(OrderConfig orderConfig) {
        this.orderConfig = orderConfig;
        preCheck();
    }

    /**
     * 通过比较指定字段来比较两个元素
     */
    @Override
    public int compare(Object entity1, Object entity2) {
        Comparable comparable1 = getFieldValue(entity1);
        Comparable comparable2 = getFieldValue(entity2);
        if (comparable1 == MIN_VALUE || comparable2 == MAX_VALUE) {
            return -1;
        }
        if (comparable1 == MAX_VALUE || comparable2 == MIN_VALUE) {
            return 1;
        }
        return comparable1.compareTo(comparable2);
    }

    /**
     * 获取元素的字段值（用于比较）
     * 并进行映射转换、极值处理
     *
     * @param entity 元素
     * @return 用于比较的字段值
     */
    public Comparable getFieldValue(Object entity) {
        Object fieldValue = null;
        try {
            // 反射获取元素的字段值
            fieldValue = fieldMethod.invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("获取类型: " + entityClass + " 中的字段: " + orderConfig.getFieldName() + " 失败");
        }
        // 特殊值判断：如果符合最大值或最小值，直接返回
        if (orderConfig.getMaxOneValue() != null && orderConfig.getMaxOneValue().contains(fieldValue)) {
            return MAX_VALUE;
        }
        if (orderConfig.getMinOneValue() != null && orderConfig.getMinOneValue().contains(fieldValue)) {
            return MIN_VALUE;
        }
        // 如果设置了字段映射器，则进行转换
        if (orderConfig.getMapper() == null) {
            return (Comparable) fieldValue;
        }
        try {
            return orderConfig.getMapper().apply(fieldValue);
        } catch (Exception e) {
            return orderConfig.getMapErrorMode() == 0 ? MIN_VALUE : MAX_VALUE;
        }

    }

    /**
     * 排序前的检查，保证 fieldMethod 得到更新
     */
    private void preCheck() {
        try {
            fieldMethod = entityClass.getMethod("get" + orderConfig.getFieldName().substring(0, 1).toUpperCase() + orderConfig.getFieldName().substring(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderConfig getOrderConfig() {
        return orderConfig;
    }

    public void setOrderConfig(OrderConfig orderConfig) {
        this.orderConfig = orderConfig;
    }
}
