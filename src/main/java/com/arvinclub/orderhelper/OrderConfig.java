/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderParam
 * Author: 01397386
 * Date: 2020/10/27 17:46
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class OrderConfig {

    public static boolean ASC_MODE = false;
    public static boolean DESC_MODE = true;
    public static int MIN_ON_ERROR = 1;
    public static int MAX_ON_ERROR = 2;

    // 要排序的字段名
    private String fieldName;
    // 排序模式 false升序，true降序
    private boolean orderMode;

    // 最大值
    private Set<Object> maxOnValues;
    // 最小值
    private Set<Object> minOnValues;

    // 字段映射器
    private Function<Object, Comparable> mapper;
    //映射出错时判断 0抛出异常，1最小，2最大
    private int mapErrorMode;

    public void addMaxValue(Object maxOnValue) {
        if (maxOnValues == null) {
            maxOnValues = new HashSet<>();
        }
        maxOnValues.add(maxOnValue);
    }

    public void addMinValue(Object minOnValue) {
        if (minOnValues == null) {
            minOnValues = new HashSet<>();
        }
        minOnValues.add(minOnValue);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(boolean orderMode) {
        this.orderMode = orderMode;
    }

    public Set<Object> getMaxOnValues() {
        return maxOnValues;
    }

    public void setMaxOnValues(Set<Object> maxOnValues) {
        this.maxOnValues = maxOnValues;
    }

    public Set<Object> getMinOnValues() {
        return minOnValues;
    }

    public void setMinOnValues(Set<Object> minOnValues) {
        this.minOnValues = minOnValues;
    }

    public Function<Object, Comparable> getMapper() {
        return mapper;
    }

    public void setMapper(Function<Object, Comparable> mapper) {
        this.mapper = mapper;
    }

    public int getMapErrorMode() {
        return mapErrorMode;
    }

    public void setMapErrorMode(int mapErrorMode) {
        this.mapErrorMode = mapErrorMode;
    }

}
