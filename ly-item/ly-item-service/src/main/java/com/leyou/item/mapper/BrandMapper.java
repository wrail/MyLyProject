package com.leyou.item.mapper;

import com.leyou.item.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface BrandMapper extends Mapper<Brand> {

    //insert into leyou.tb_category_brand(category_id,brand_id) values(cid,bid)
    @Insert("insert into leyou.tb_category_brand (category_id, brand_id) values (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid,@Param("bid") Long bid);
}
