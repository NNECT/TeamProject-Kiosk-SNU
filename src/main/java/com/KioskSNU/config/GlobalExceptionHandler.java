package com.KioskSNU.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllErrors(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        int status = response.getStatus();
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());  // application context 제거
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorCode", status);
        mav.addObject("errorMessage", ex.getMessage());

        if (path.startsWith("/inside")) {
            mav.setViewName("/inside/inside_error"); // 'inside' 경로에 대한 에러 페이지
        } else if (path.startsWith("/outside")) {
            mav.setViewName("/outside/outside_error"); // 'outside' 경로에 대한 에러 페이지
        } else {
            mav.setViewName("/error"); // 그 외에 대한 기본 에러 페이지
        }

        return mav;
    }
}
