package com.leyou.common.exception;


import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
*
* 比如简单的在controller使用如下
*    throw new LyException(ExceptionEnum.PRICE_CANNOT_NULL);
* */


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

}



