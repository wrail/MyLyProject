package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_category")
public class Category {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
}
