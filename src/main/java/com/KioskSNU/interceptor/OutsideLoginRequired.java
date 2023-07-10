package com.KioskSNU.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 사용자 로그인이 필요한 Outside Controller 메소드에 붙이는 Annotation.
 * 해당 Annotation이 붙은 메소드는 OutsideLoginInterceptor를 거쳐 사용자 로그인 여부를 확인하게 되며,
 * 로그인이 되어있지 않다면 Outside 로그인 페이지로 redirect 된다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OutsideLoginRequired {}
