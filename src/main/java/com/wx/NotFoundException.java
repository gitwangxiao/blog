package com.wx;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wx
 * @Description
 * @date 2020/8/12 16:48
 */

@ResponseStatus(HttpStatus.NOT_FOUND)   //注解表示资源找不到的状态码，标识了状态码的时候就不拦截
public class NotFoundException extends RuntimeException {//重写RuntimeException的方法
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
