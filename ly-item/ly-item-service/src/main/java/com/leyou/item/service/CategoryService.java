package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper mapper;



    public List<Category> quaryListById(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = mapper.select(category);
        //使用一个util中的判空，里包含有判空的所有条件
        if (CollectionUtils.isEmpty(list)){
            //抛出自定义异常404
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
