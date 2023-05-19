package com.chen.property.interceptor;

import com.chen.property.pojo.Info;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Info info = (Info)request.getSession().getAttribute("Info");
        return !(info==null || !"1".equals(info.getType()));
    }
}
