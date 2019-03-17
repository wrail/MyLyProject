package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
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

    //改进后的异常处理
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }


    //原始的，很麻烦而且很不通用，并且不能显示一个友好的错误信息！
/*
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {

//        就是处理BAD-REQUEST返回给前台的异常信息，message就是runtimeException里的参数字符串如下
//        throw new RuntimeException("不能查询到");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
*/

}
