package com.walkyren.demo.controller;

import com.walkyren.demo.domain.entity.Admin;
import com.walkyren.demo.dto.AdminDto;
import com.walkyren.demo.exceptions.base.DemoException;
import com.walkyren.demo.services.AdminService;
import com.walkyren.demo.util.Response;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@SuppressWarnings("WeakerAccess")
@RestController
@RequestMapping(value = "/v1/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Resource
    private AdminService adminService;

    /**
     * @param accountName     账户名
     * @param accountPassword 密码
     * @return 管理员账户信息
     * @function 管理员登陆
     * @method PUT
     */
    @PutMapping("/admin")
    public Response adminLoginRoute(@RequestParam("account_name") String accountName,
                                    @RequestParam("account_password") String accountPassword) {
        Response result = new Response();
        try {
            Admin admin = adminService.adminLogin(accountName, accountPassword);
            HashMap<String, Object> res = new AdminDto(admin).dataDTO();
            result.success(res);
        } catch (DemoException e) {
            result.failed(e);
        }
        return result;
    }


}
