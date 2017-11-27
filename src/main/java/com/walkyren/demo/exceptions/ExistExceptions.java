package com.walkyren.demo.exceptions;

import com.walkyren.demo.constant.exceptions.UserExceptionsConstants;
import com.walkyren.demo.exceptions.base.DemoException;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
public class ExistExceptions extends DemoException {
    private ExistExceptions(Integer code, String msg) {
        super(code, msg);
    }

    public static ExistExceptions accountNotExist() {
        return new ExistExceptions(
                UserExceptionsConstants.ACCOUNTNOTEXISTCODE,
                UserExceptionsConstants.ACCOUNTNOTEXISTMSG

        );
    }

    public static ExistExceptions accountAlreadyExist() {
        return new ExistExceptions(
                UserExceptionsConstants.ACCOUNTALREADYEXISTCODE,
                UserExceptionsConstants.ACCOUNTALREADYEXISTMSG
        );
    }

}
