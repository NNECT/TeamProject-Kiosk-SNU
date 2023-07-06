package com.KioskSNU.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service("logger")
@Aspect
public class Logger {
    @Before("execution(* com.KioskSNU.view..*.*(..))")
    public void viewLog(JoinPoint jp) {
        System.out.println("[공통 로그] 뷰 메서드 수행 - " + jp.getSignature().getName() + "(): " + jp.getSignature().toLongString());
    }

    @Before("execution(* com.KioskSNU.snu..*.*(..))")
    public void snuLog(JoinPoint jp) {
        System.out.println("[공통 로그] 서비스 메서드 수행 - " + jp.getSignature().getName() + "(): " + jp.getSignature().toLongString());
    }

    @Before("execution(* com.KioskSNU.interceptor..*.*(..))")
    public void interceptorLog(JoinPoint jp) {
        System.out.println("[공통 로그] 인터셉터 메서드 수행 - " + jp.getSignature().getName() + "(): " + jp.getSignature().toLongString());
    }
}
