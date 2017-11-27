package com.walkyren.demo.modules.security.authorization.interceptor;

import com.walkyren.demo.domain.entity.Admin;
import com.walkyren.demo.domain.repository.AdminRepo;
import com.walkyren.demo.modules.security.authorization.annotation.CurrentAdmin;
import com.walkyren.demo.modules.security.config.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.annotation.Resource;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Component
public class AdminInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private AdminRepo adminRepo;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Admin.class) && parameter.hasParameterAnnotation(CurrentAdmin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        String session = (String) webRequest.getAttribute(Constants.ADMIN_INFO, RequestAttributes.SCOPE_REQUEST);
        if (session != null) {
            //从数据库中查询并返回
            return adminRepo.findBySession(session);
        }
        throw new MissingServletRequestPartException(Constants.ADMIN_INFO);
    }
}
