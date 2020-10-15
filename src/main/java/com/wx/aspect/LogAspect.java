package com.wx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wx
 * @Description
 * @date 2020/8/12 18:03
 */

@Aspect
@Component
public class LogAspect {
    private final Logger logger =LoggerFactory.getLogger(this.getClass());

    //@Pointcut 表明这是一个切面
    // 【修饰符】 返回值类型 包名.类名.方法名.(参数)
    @Pointcut("execution(* com.wx.controller.*.*(..))")  //拦截wen下的所有参数类型，所有返回值类型的所有类的所有方法
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取URL、IP
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
//        logger.info("---------doBefore---------");
    }
    @After("log()")
    public void doAfter(){
        logger.info("---------doAfter----------");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result: {}",result);
    }


    //    封装请求参数
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
