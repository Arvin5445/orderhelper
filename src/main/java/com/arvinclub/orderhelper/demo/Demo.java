/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Demo
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper.demo;


import com.arvinclub.orderhelper.OrderUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>() {{
            add(new Student(456, "施航程", 22, "569%"));
            add(new Student(1234, "施航程", 54, "5.6%"));
            add(new Student(34, "施航程", 12, "56.54%"));
            add(new Student(33, "施航程", 6, "--"));
            add(new Student(24, "施航程", 546, "68.57%"));
            add(new Student(9999, "施航程", 56, "99.99%"));
            add(new Student(0, "施航程", 545, "77.77%"));
            add(new Student(0, "施航程", 545, "MAX"));
            add(new Student(75, "施航程", 9999, "56%"));
            add(new Student(75, "施航程", 9999, "-56%"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(4396, "施航程", 545, "100%"));
            add(new Student(8, "施航程", 42, "0%"));
            add(new Student(7, "施航程", 16, "43.85%"));
        }};
        OrderUtils.orderDesc(studentList, "promotionRate", (p) -> new BigDecimal(((String) p).substring(0, ((String) p).length() - 1)));
        System.out.println(studentList);
    }
}
