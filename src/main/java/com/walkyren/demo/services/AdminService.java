package com.walkyren.demo.services;

import com.walkyren.demo.domain.entity.Admin;

/**
 * Author: daxian
 * Date: 2017/11/27
 */

public interface AdminService {
    Admin adminLogin(String account, String password);

    Admin adminAdd(String account, String password);

    Admin changeMypwd(String old_password, String new_password,String account);
}
