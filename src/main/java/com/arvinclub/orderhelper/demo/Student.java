/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Student
 * Author: 01397386
 * Date: 2020/10/26 11:15
 * Description:
 */
package com.arvinclub.orderhelper.demo;

import com.arvinclub.orderhelper.OrderBase;

public class Student extends OrderBase {
    private int id;
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() { }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
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

}
