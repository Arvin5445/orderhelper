/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderParam
 * Author: 01397386
 * Date: 2020/10/27 17:46
 * Description:
 */
package com.arvinclub.orderhelper;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class OrderConfig {
    // 要排序的集合
    private List list;
    // 要排序的字段名
    private String fieldName;
    // 排序模式 0升序，1降序
    private int orderMode;

    // 最大值
    private Set<Object> maxOneValue;
    // 最小值
    private Set<Object> minOneValue;

    // 字段映射器
    private Function<Object, Comparable> mapper;
    //映射出错时判断 0最小，1最大
    private int mapErrorMode;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public int getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(int orderMode) {
        this.orderMode = orderMode;
    }
}
