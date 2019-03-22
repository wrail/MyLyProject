package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 给枚举类提供构造方法，和get方法，枚举中构造函数默认都是私有的
 * */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_NULL(400, "价格不能为空"),
    CATEGORY_NOT_FOUND(404, "商品未找到"),
    BRANDER_NOT_FOUND(404, "品牌不存在"),
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    INVALID_FILE_TYPE(500, "文件类型不符"),
    UPLOAD_ERROR(400, "文件上传失败");

//    CATEGORY_BRAND_SAVE_ERROR(500,"新增品牌分类失败");

    private Integer code;
    private String msg;

}
