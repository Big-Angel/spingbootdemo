package com.walkyren.demo.exceptions;

import com.walkyren.demo.constant.exceptions.ParamsExceptionsConstants;
import com.walkyren.demo.exceptions.base.DemoException;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
public class ParamsExceptions extends DemoException {
    private ParamsExceptions(Integer code, String msg) {
        super(code, msg);
    }

    public static ParamsExceptions inputParamsException() {
        return new ParamsExceptions(
                ParamsExceptionsConstants.INPUTPARAMSCODE,
                ParamsExceptionsConstants.INPUTPARAMSMSG
        );
    }

    public static ParamsExceptions passwordErrorException() {
        return new ParamsExceptions(
                ParamsExceptionsConstants.PWDERRORCODE,
                ParamsExceptionsConstants.PWDERRORMSG
        );
    }
}
