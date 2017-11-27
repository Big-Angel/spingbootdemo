package com.walkyren.demo.dto;

import com.walkyren.demo.domain.entity.Admin;

import java.util.HashMap;

/**
 * Author: daxian
 * Date: 2017/11/27
 * Desc: 对返回的数据进行封装
 */
public class AdminDto {
    private HashMap<String, Object> map = new HashMap<>();

    public AdminDto(Admin admin) {
        this.map.put("account_name", admin.getAccountName());
        this.map.put("account_id", admin.getId());
        this.map.put("session", admin);
    }

    public HashMap<String, Object> dataDTO() {
        return this.map;
    }
}
