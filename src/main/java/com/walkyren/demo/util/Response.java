package com.walkyren.demo.util;

import com.walkyren.demo.exceptions.base.DemoException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daxian
 * Date: 2017/11/27
 * Desc: 请求相应数据封装
 */
@Getter
@Setter
public class Response {

    private Integer status;
    private String message;
    private Map<String, Object> data;

    public Response() {
        this.status = 0;
        this.message = "success";
        this.data = new HashMap<>();
    }

    public Response(Integer status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public void addParams(String key, Object value) {
        this.data.put(key, value);
    }

    public Response success(String key, Object res) {
        this.status = 0;
        this.message = "success";
        this.data.put(key, res);
        return this;
    }

    public Response success(HashMap<String, Object> res) {
        this.status = 0;
        this.message = "success";
        this.data = res;
        return this;
    }

    public Response failed(Integer status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
        return this;
    }

    public Response failed(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
        return this;
    }

    public Response failed(DemoException e) {
        this.status = e.getCode();
        this.message = e.getMsg();
        this.data = null;
        return this;
    }
}
