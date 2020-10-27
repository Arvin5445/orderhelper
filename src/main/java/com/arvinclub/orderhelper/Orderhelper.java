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
import java.util.Set;
import java.util.function.Function;

/**
 * 辅助比较器
 */
public class Orderhelper implements Comparator {

    private static final Comparable MAX_VALUE = o -> 1;
    private static final Comparable MIN_VALUE = o -> -1;

    // 要排序的字段名
    private String fieldName;
    // 获取字段的 Get 方法
    private Method fieldMethod;
    // 元素类型
    private Class entityClass;
    // 最大值
    private Set<Object> maxOneValue;
    // 最小值
    private Set<Object> minOneValue;

    // 字段映射器
    private Function<Object, Comparable> mapper;
    //映射出错时判断 0最小，1最大
    private int mapErrorMode;

    public Orderhelper(String orderParamName, Class elementClass) {
        this.fieldName = orderParamName;
        this.entityClass = elementClass;
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
            throw new RuntimeException("获取类型: " + entityClass + " 中的字段: " + fieldName + " 失败");
        }
        // 特殊值判断：如果符合最大值或最小值，直接返回
        if (maxOneValue != null && maxOneValue.contains(fieldValue)) {
            return MAX_VALUE;
        }
        if (minOneValue != null && minOneValue.contains(fieldValue)) {
            return MIN_VALUE;
        }
        // 如果设置了字段映射器，则进行转换
        if (mapper == null) {
            return (Comparable) fieldValue;
        }
        try {
            return mapper.apply(fieldValue);
        } catch (Exception e) {
            return mapErrorMode == 0 ? MIN_VALUE : MAX_VALUE;
        }

    }

    /**
     * 排序前的检查，保证 fieldMethod 得到更新
     */
    private void preCheck() {
        try {
            fieldMethod = entityClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
        } catch (Exception e) {
            throw new RuntimeException("获取类型: " + entityClass + " 中的字段: " + fieldName + " 失败");
        }
    }

    public Function<Object, Comparable> getMapper() {
        return mapper;
    }

    public void setMapper(Function<Object, Comparable> mapper) {
        this.mapper = mapper;
    }

    public Set<Object> getMaxOneValue() {
        return maxOneValue;
    }

    public void setMaxOneValue(Set<Object> maxOneValue) {
        this.maxOneValue = maxOneValue;
    }

    public Set<Object> getMinOneValue() {
        return minOneValue;
    }

    public void setMinOneValue(Set<Object> minOneValue) {
        this.minOneValue = minOneValue;
    }

    public int getMapErrorMode() {
        return mapErrorMode;
    }

    public void setMapErrorMode(int mapErrorMode) {
        this.mapErrorMode = mapErrorMode;
    }
}
