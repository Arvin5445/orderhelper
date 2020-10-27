/*
 * Copyright (C), 2020, 顺丰科技
 * FileName: OrderException
 * Author: 01397386
 * Date: 2020/10/27 19:36
 * Description:
 */
package com.arvinclub.orderhelper;

public class OrderException extends RuntimeException {
    public OrderException() {
        super();
    }

    public OrderException(String msg) {
        super(msg);
    }
}
