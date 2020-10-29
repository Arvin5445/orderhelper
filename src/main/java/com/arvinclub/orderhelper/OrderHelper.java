/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Orderhelper
 * Author: 01397386
 * Date: 2020/10/27 10:52
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.Comparator;
import java.util.List;

/**
 * 辅助比较器
 */
public class OrderHelper<T> implements Comparator<T> {

    // 最大值
    private static final Comparable<?> MAX_VALUE = o -> 1;
    // 最小值
    private static final Comparable<?> MIN_VALUE = o -> -1;
    // 配置信息
    private final List<OrderConfig<T>> orderConfigs;
    // 要排序的集合的元素类型

    OrderHelper(List<OrderConfig<T>> orderConfigs) {
        if (orderConfigs == null || orderConfigs.isEmpty()) {
            throw new NullPointerException("orderConfig 未设置");
        }
        this.orderConfigs = orderConfigs;
    }

    /**
     * 通过比较指定字段来比较两个元素
     */
    @Override
    public int compare(T entity1, T entity2) {
        int result = 0;
        // 如果字段值相等，比较下一层的字段
        for (int depth = 0; result == 0 && depth < orderConfigs.size(); depth++) {
            if (entity1.equals(entity2)) {
                continue;
            }
            Comparable comparable1 = getFieldValue(entity1, depth);
            Comparable<?> comparable2 = getFieldValue(entity2, depth);
            // 极值判断
            boolean orderMode = orderConfigs.get(depth).getOrderMode();
            if (comparable2 == MAX_VALUE) {
                return orderMode ? 1 : -1;
            }
            if (comparable2 == MIN_VALUE) {
                return orderMode ? -1 : 1;
            }
            result = comparable1.compareTo(comparable2);
            // 升序降序判断
            result = orderMode ? -result : result;
        }
        return result;
    }

    /**
     * 获取元素的字段值（用于比较）
     * 并进行映射转换、极值处理
     *
     * @param entity 元素
     * @return 用于比较的字段值
     */
    public Comparable<?> getFieldValue(T entity, int depth) {
        OrderConfig<T> orderConfig = orderConfigs.get(depth);
        Comparable<?> fieldValue;
        try {
            // 获取元素的字段值
            fieldValue = orderConfig.getMapper().apply(entity);
        } catch (Exception e) {
            if (orderConfig.getMapErrorMode() == 1) {
                return MIN_VALUE;
            }
            if (orderConfig.getMapErrorMode() == 2) {
                return MAX_VALUE;
            }
            throw new OrderException("在获取比较值时抛出异常: " + e);
        }
        // 特殊值判断：如果符合最大值或最小值，直接返回极值
        if (orderConfig.getMaxOnValues() != null && orderConfig.getMaxOnValues().contains(fieldValue)) {
            return MAX_VALUE;
        }
        if (orderConfig.getMinOnValues() != null && orderConfig.getMinOnValues().contains(fieldValue)) {
            return MIN_VALUE;
        }
        return fieldValue;
    }

}
