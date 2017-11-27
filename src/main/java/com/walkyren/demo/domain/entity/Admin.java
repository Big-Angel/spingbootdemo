package com.walkyren.demo.domain.entity;

import com.walkyren.demo.util.MD5Util;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Entity
@Table(name = "eplus_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "passwd")
    private String password;

    @Column(name = "session")
    private String session;

    public Admin() {
    }

    public void setSession() {
        this.session = UUID.randomUUID().toString().replace("-", "");
    }

    public Admin(String account, String password) {
        this.accountName = account;
        this.password = password;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setPassword(String password) {
        this.password = MD5Util.hashencode(password);
    }

    public Long getId() {
        return this.id;
    }

    public boolean checkPassword(String password) {
        password = MD5Util.hashencode(password);
        return password.equals(this.password);

    }

}
