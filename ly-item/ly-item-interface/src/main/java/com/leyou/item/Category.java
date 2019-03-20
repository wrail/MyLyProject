package com.leyou.item;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "tb_category")
@Data
public class Category {
    @Id
//        @GeneratedValue(strategy= GenerationType.IDENTITY)
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
    // getter和setter略
    // 注意isParent的get和set方法
}

