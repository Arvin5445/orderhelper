/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Demo
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper.demo;


import com.arvinclub.orderhelper.OrderUtils;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>(){{
            add(new Student(456,"施航程", 22));
            add(new Student(1234,"施航程", 54));
            add(new Student(34,"施航程", 12));
            add(new Student(33,"施航程", 6));
            add(new Student(24,"施航程", 546));
            add(new Student(9999,"施航程", 56));
            add(new Student(0,"施航程", 545));
            add(new Student(75,"施航程", 9999));
            add(new Student(9527,"施航程", 0));
            add(new Student(4396,"施航程", 545));
            add(new Student(8,"施航程", 42));
            add(new Student(7,"施航程", 16));
        }};
        OrderUtils.orderDesc(studentList,"age");
        System.out.println(studentList);
    }
}
