package com.wx.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wx
 * @Description  拦截器，没有登陆就不允许访问
 * @date 2020/8/14 0:16
 */

public class LoginInterceptor implements HandlerInterceptor {

        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
            if (request.getSession().getAttribute("user")==null){
                response.sendRedirect("/admin");
                return false;
            }
            return true;
        }
}
