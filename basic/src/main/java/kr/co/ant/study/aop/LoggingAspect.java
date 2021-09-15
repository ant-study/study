package kr.co.ant.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    //모든 메소드 실행전
    @Before("execution(public * *(..))")
    public void beforeLogging(JoinPoint joinPoint){
        log.info("Before Advice Argument :: {}", joinPoint.getArgs());
    }

    //모든 메소드 실행후
    @After("execution(public * *(..))")
    public void afterLogging(JoinPoint joinPoint){
        log.info("After Advice Method End :: {}", joinPoint.getSignature().getName());
    }

    //모든 메소드 실행후 Return 값 출력
    @AfterReturning(value = "execution(public * *(..))", returning = "result")
    public void afterReturningLogging(JoinPoint joinPoint, Object result){
        log.info("AfterReturning Advice Return :: {}", result);
    }

    //Exception이 발생하면 실행
    @AfterThrowing(value = "execution(public * *(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        log.info("After Throwing Advice Exception :: {}", e.getMessage());
    }

    //모든조건 실행 전/후 Exception proceed를 호출 해줘야 함
    @Around("execution(public * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info("proceed :: {}", proceed);
        return proceed;
    }




}
