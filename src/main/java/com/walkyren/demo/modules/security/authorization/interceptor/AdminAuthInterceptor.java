package com.walkyren.demo.modules.security.authorization.interceptor;

import com.walkyren.demo.constant.GlobalConstants;
import com.walkyren.demo.modules.security.authorization.annotation.AdminAuth;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.walkyren.demo.modules.security.config.Constants.ADMIN_INFO;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Component
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        /* 没有映射到方法，不验证 */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 从header中得到token
        String authorization = request.getHeader(GlobalConstants.AUTHORIZATION);

        if (authorization != null) {
            request.setAttribute(ADMIN_INFO, authorization);
            return true;
        }

        //如果验证token失败，并且方法注明了 AdminAuth，返回401错误
        if (method.getAnnotation(AdminAuth.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
