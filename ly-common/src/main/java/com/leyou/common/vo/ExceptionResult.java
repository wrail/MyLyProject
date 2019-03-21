package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;

public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum e){
        this.status = e.getCode();
        this.message = e.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
