//package com.qdu.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object user = request.getSession().getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/bbs/login"); // 未登录，重定向到登录页面
//            return false;
//        }
//        return true; // 已登录，继续请求
//    }
//}
