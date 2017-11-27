package com.walkyren.demo.domain.repository;

import com.walkyren.demo.domain.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Repository
public interface AdminRepo extends CrudRepository<Admin, Long> {
    Admin findByAccountName(String accountName);

    Admin findBySession(String session);
}
