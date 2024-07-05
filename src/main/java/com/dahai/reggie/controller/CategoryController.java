package com.dahai.reggie.controller;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahai.reggie.common.R;
import com.dahai.reggie.entity.Category;
import com.dahai.reggie.entity.Employee;
import com.dahai.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("新增分类信息：{}", category.toString());
        categoryService.save(category);
        return R.success("新增分类成功");
    }
    @GetMapping("/page")
    public  R<Page> page(int page, int pageSize){
        log.info("page={},pageSize={}",page,pageSize);
        Page pageInfo = new Page(page,pageSize);
        QueryWrapper<Category> wrapper =  new QueryWrapper<Category>();
        wrapper.orderByAsc("sort");
        categoryService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }
}
