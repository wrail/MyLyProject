package com.leyou.item.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.Brand;
import com.leyou.item.mapper.BrandMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult qurayBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //1.分页,使用分页助手，必须放在查询前对查询进行拦截
        PageHelper.startPage(page, rows);
        /*
         * where 'name' like "%xxxx%"
         *        or key == 'x'
         *        order by id desc
         * */
        //2.过滤,生成一个example实例，并且传入Brand类,letter在数据库存的是大写
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria()
                    .orLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        //3.排序,只需要告诉按什么进行那种排序,使用三元运算符进行选择
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //4.查询
        List<Brand> list = brandMapper.selectByExample(example);
        //5.判断
        if (list.isEmpty()) {
            throw new LyException(ExceptionEnum.BRANDER_NOT_FOUND);
        }
        //解析分页结果,在分页助手中，已经将list转为PageInfo
        PageInfo pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(), list);

    }

    @Transactional
    public void saveBrands(Brand brand, List<Long> cids) {
        //新增品牌
        //Id为自增因此不用给id传值
        brand.setId(null);
        int flag = brandMapper.insert(brand);
        if (flag != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //给中间表加数据
        for (Long cid : cids) {
            //如果插入成功id会回显
            flag = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (flag != 1) {
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }
}
