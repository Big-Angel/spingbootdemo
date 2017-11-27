package com.walkyren.demo.controller;

import com.walkyren.demo.domain.entity.Admin;
import com.walkyren.demo.dto.AdminDto;
import com.walkyren.demo.exceptions.base.DemoException;
import com.walkyren.demo.modules.security.authorization.annotation.AdminAuth;
import com.walkyren.demo.modules.security.authorization.annotation.CurrentAdmin;
import com.walkyren.demo.services.AdminService;
import com.walkyren.demo.util.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@SuppressWarnings("WeakerAccess")
@RestController
@RequestMapping(value = "/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * @param accountName     -->添加的账户名
     * @param accountPassword -->添加账户的密码
     * @return 添加的管理员账户信息
     * @function 添加管理员
     * @auth Admin
     * @method PUT
     */
    @PutMapping("/add_admin")
    @AdminAuth
    public Response addAdminRoute(@RequestParam("account_name") String accountName,
                                  @RequestParam("account_password") String accountPassword) {

        Response result = new Response();

        try {

            Admin adminUser = adminService.adminAdd(accountName, accountPassword);
            result.success(new AdminDto(adminUser).dataDTO());

        } catch (DemoException e) {
            result.failed(e);
        }
        return result;
    }

    /**
     * @param old_password -->旧密码
     * @param new_password -->新密码
     * @param admin        -->登陆账号session中的信息
     * @return admin账户信息
     * @function 管理员修改密码
     * @auth Admin
     * @method POST
     */
    @PostMapping("/change_mypwd")
    @AdminAuth
    public Response changePwdRoute(@RequestParam String old_password,
                                   @RequestParam String new_password,
                                   @CurrentAdmin Admin admin) {
        Response result = new Response();
        try {

            admin = adminService.changeMypwd(old_password, new_password, admin);
            result.success(new AdminDto(admin).dataDTO());

        } catch (DemoException e) {
            result.failed(e);
        }
        return result;
    }
}
