package com.leyou.item.web;


import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
   @Autowired
    private CategoryService service;
   @GetMapping("/list")
    public ResponseEntity<List<Category>> quaryCategoryById(@RequestParam("pid") Long pid){

       return ResponseEntity.ok(service.quaryListById(pid));
   }

}
