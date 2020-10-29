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

public class OrderConfig<T> {
    public static final boolean ASC_MODE = false;
    public static final boolean DESC_MODE = true;
    public static final int ERROR_MODE = 0;
    public static final int MIN_MODE = 1;
    public static final int MAX_MODE = 2;

    // 排序模式 false升序，true降序
    private boolean orderMode;
    // 最大值
    private Set<Object> maxOnValues;
    // 最小值
    private Set<Object> minOnValues;
    // 通过实体获取字段值的映射器
    private Function<T, Comparable<?>> mapper;
    // 映射出错时判断 0抛出异常，1最小，2最大
    private int mapErrorMode;
    // 字段值为null时判断 0抛出异常，1最小，2最大
    private int nullFieldMode;
    // 实体为null时判断 0抛出异常，1最小，2最大
    private int nullEntityMode;

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

    public Function<T, Comparable<?>> getMapper() {
        return mapper;
    }

    public void setMapper(Function<T, Comparable<?>> mapper) {
        this.mapper = mapper;
    }

    public int getMapErrorMode() {
        return mapErrorMode;
    }

    public void setMapErrorMode(int mapErrorMode) {
        this.mapErrorMode = mapErrorMode;
    }
    
    public int getNullFieldMode() {
        return nullFieldMode;
    }
    
    public void setNullFieldMode(int nullFieldMode) {
        this.nullFieldMode = nullFieldMode;
    }
    
    public int getNullEntityMode() {
        return nullEntityMode;
    }
    
    public void setNullEntityMode(int nullEntityMode) {
        this.nullEntityMode = nullEntityMode;
    }
}
