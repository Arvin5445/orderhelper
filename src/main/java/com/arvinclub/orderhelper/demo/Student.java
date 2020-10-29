/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Student
 * Author: 01397386
 * Date: 2020/10/26 11:15
 * Description:
 */
package com.arvinclub.orderhelper.demo;

import java.math.BigDecimal;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int age;
    private String promotionRate;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public Student(int id, String name, int age, String promotionRate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.promotionRate = promotionRate;
    }
    
    public Student() {
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", promotionRate='" + promotionRate + '\'' +
                "}\n";
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getPromotionRate() {
        return promotionRate;
    }
    
    public void setPromotionRate(String promotionRate) {
        this.promotionRate = promotionRate;
    }
    
    String xxxRate;
    BigDecimal xxxRateForOrder;
    public String getXxxRate() {
        return xxxRate;
    }
    public BigDecimal getXxxRateForOrder() {
        if (xxxRateForOrder == null) {
            xxxRateForOrder = new BigDecimal(xxxRate.substring(0, xxxRate.length() - 1));
        }
        return xxxRateForOrder;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(promotionRate, student.promotionRate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, promotionRate);
    }
}
