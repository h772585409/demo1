package com.molin.project200908.handller;

import com.molin.project200908.Util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CoustomExtHandler {
    @ExceptionHandler(value = AuthorizationException.class)
    Result handlerException() {
        return Result.fail("没有权限");
    }

    @ExceptionHandler(value = Exception.class)
    Result handlerException(Exception e, HttpServletRequest request) {
        return Result.fail("服务器挂了！！！");
    }

}
