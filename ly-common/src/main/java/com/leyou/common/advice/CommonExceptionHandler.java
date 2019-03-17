package com.leyou.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 默认拦截加了所有controller的类,将common打包依赖放在service下即可调用
 * */
@ControllerAdvice
public class CommonExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {

        //就是处理BAD-REQUEST返回给前台的异常信息，message就是runtimeException里的参数字符串如下
//        throw new RuntimeException("不能查询到");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
