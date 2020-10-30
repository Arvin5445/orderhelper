package com.arvinclub.orderhelper;

/**
 * 排序异常
 */
public class OrderException extends RuntimeException {
    public OrderException() {
        super();
    }

    public OrderException(String msg) {
        super(msg);
    }
}
