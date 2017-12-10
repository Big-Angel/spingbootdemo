package com.walkyren.demo.services.impl;

import com.walkyren.demo.domain.entity.Admin;
import com.walkyren.demo.domain.repository.AdminRepo;
import com.walkyren.demo.exceptions.ExistExceptions;
import com.walkyren.demo.exceptions.ParamsExceptions;
import com.walkyren.demo.exceptions.base.DemoException;
import com.walkyren.demo.services.AdminService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminRepo adminRepo;

    @ExceptionHandler
    @Override
    public Admin adminLogin(String account, String password) throws DemoException {
        if (account != null && !account.isEmpty()) {
            Admin admin = adminRepo.findByAccountName(account);
            if (admin != null) {
                if (admin.checkPassword(password)) {
                    admin.setSession();
                    return admin;
                } else {
                    throw ParamsExceptions.passwordErrorException();
                }
            } else {
                throw ExistExceptions.accountNotExist();
            }
        } else {
            throw ParamsExceptions.inputParamsException();
        }
    }

    public Admin adminAdd(String account, String password) {
        if (account != null && !account.isEmpty() && password != null && !password.isEmpty()) {
            Admin admin = adminRepo.findByAccountName(account);
            if (admin == null) {
                admin = new Admin();
                admin.setAccountName(account);
                admin.setPassword(password);
                admin = adminRepo.save(admin);
            } else {
                throw ExistExceptions.accountAlreadyExist();
            }
            return admin;
        } else {
            throw ParamsExceptions.inputParamsException();
        }
    }

    public Admin changeMypwd(String old_password, String new_password,String account) {
        Admin admin = adminRepo.findByAccountName(account);
        if (old_password != null && !old_password.isEmpty()) {
            if (admin.checkPassword(old_password)) {
                admin.setPassword(new_password);
                return adminRepo.save(admin);
            } else {
                throw ParamsExceptions.passwordErrorException();
            }
        } else {
            throw ParamsExceptions.inputParamsException();
        }
    }

}
