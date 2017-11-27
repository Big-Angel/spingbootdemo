package com.walkyren.demo.exceptions.base;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
public class DemoException extends RuntimeException {
    private Integer code;
    private String msg;

    public DemoException() {
        super();
    }

    public DemoException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
