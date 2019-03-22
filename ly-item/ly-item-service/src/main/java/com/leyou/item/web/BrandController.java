package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    BrandService brandService;
    /*
    * 分页查询品牌
    * */
    @GetMapping("/page")
    public ResponseEntity<PageResult> quaryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,//可以不用传值
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,//默认为升序
            @RequestParam(value = "key", required = false) String key
    ){
        PageResult result = brandService.qurayBrandByPage(page,rows,sortBy,desc,key);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Void> saveBrands(Brand brand, @RequestParam("cids") List<Long> cids){
        brandService.saveBrands(brand,cids);
        //created 代表保存成功，不需要返回参数就使用build,否则就是要body
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
