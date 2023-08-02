package com.KioskSNU.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginInterceptor implements HandlerInterceptor {
    private static final String LOGIN_URI = "/admin/adminlogin";

    /**
     * 관리자 페이지 로그인 확인 인터셉터
     * 해당 컨트롤러가 AdminLoginRequired 어노테이션을 가지고 있는지 확인
     * @return 로그인이 되어있으면, 혹은 로그인이 필요하지 않으면 true, 아니면 false
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;
        if (((HandlerMethod) handler).getMethodAnnotation(AdminLoginRequired.class) == null) return true;

        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + LOGIN_URI);
            return false;
        }
        return true;
    }
}
