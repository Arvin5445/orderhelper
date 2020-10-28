/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: Demo
 * Author: 01397386
 * Date: 2020/10/27 11:10
 * Description:
 */
package com.arvinclub.orderhelper.demo;

import com.arvinclub.orderhelper.OrderConfig;
import com.arvinclub.orderhelper.OrderUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.arvinclub.orderhelper.OrderConfig.MIN_ON_ERROR;

public class Demo {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>() {{
            add(new Student(456, "施航程", 22, "569%"));
            add(new Student(1234, "施航程", 54, "5.6%"));
            add(new Student(34, "施航程", 12, "56.54%"));
            add(new Student(33, "施航程", 6, "--"));
            add(new Student(24, "施航程", 546, "68.57%"));
            add(new Student(24, "施航程", 546, "????"));
            add(new Student(9999, "施航程", 56, "MAX"));
            add(new Student(34, "施航程", 12, "56.54%"));
            add(new Student(546546546, "施航程", 3245, "99.9%"));
            add(new Student(9999, "施航程", 54, "lsd"));
            add(new Student(9999, "施航程", 57, "99.98%"));
            add(new Student(9999, "施航程", 7, "--"));
            add(new Student(9999, "施航程", 356, "99.9%"));
            add(new Student(456, "施航程", 22, "569%"));
            add(new Student(9999, "施航程", 356, "99.9%"));
            add(new Student(995455599, "施航程", 345, "99.99%"));
            add(new Student(3325436, "施航程", 3245, "99.9%"));
            add(new Student(9999, "施航程", 3245, "99.99%"));
            add(new Student(546546546, "施航程", 3245, "99.9%"));
            add(new Student(9999, "施航程", 3245, "99.99%"));
            add(new Student(9999, "施航程", 325, "99.9%"));
            add(new Student(9999, "施航程", 2, "99.99%"));
            add(new Student(9999, "施航程", 356, "99.9%"));
            add(new Student(9999, "施航程", 1, "99.99%"));
            add(new Student(9999, "施航程", -1, "99.9%"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(0, "施航程", 545, "77.77%"));
            add(new Student(0, "施航程", 545, "MAX"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(-1, "施航程", 545, "MAX"));
            add(new Student(9999, "施航程", 1, "99.99%"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(75, "施航程", 9999, "57%"));
            add(new Student(75, "施航程", 9999, "57%"));
            add(new Student(75, "施航程", 9999, "-56%"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(4396, "施航程", 545, "100%"));
            add(new Student(8, "施航程", 42, "0%"));
            add(new Student(9527, "施航程", 0, "9999%"));
            add(new Student(7, "施航程", 16, "43.85%"));
            add(new Student(9527, "施航程", 0, "9999%"));
        }};


        OrderConfig<Student> id = new OrderConfig<>();
        id.setMapper(Student::getId);
//        id.addMaxValue(-1);
        id.addMinValue("--");
        id.setOrderMode(OrderConfig.ASC_MODE);
        id.setMapErrorMode(MIN_ON_ERROR);


        OrderConfig<Student> age = new OrderConfig<>();
        age.setMapper(Student::getAge);
        age.setOrderMode(OrderConfig.ASC_MODE);
//        age.addMinValue(54);


        OrderConfig<Student> promotionRate = new OrderConfig<>();
        promotionRate.setOrderMode(OrderConfig.DESC_MODE);
        promotionRate.addMaxValue("MAX");
        promotionRate.addMinValue("--");
        promotionRate.setMapErrorMode(MIN_ON_ERROR);
        promotionRate.setMapper((p) -> new BigDecimal(p.getPromotionRate().substring(0, p.getPromotionRate().length() - 1)));

        OrderUtils.order(studentList, new ArrayList<OrderConfig<Student>>() {{
            add(id);
            add(promotionRate);
            add(age);
        }});
        System.out.println(studentList);


//        OrderUtils.order(studentList, Student::getAge);
//        System.out.println(studentList);
    }


}
