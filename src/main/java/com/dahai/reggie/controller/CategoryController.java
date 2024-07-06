package com.dahai.reggie.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahai.reggie.common.R;
import com.dahai.reggie.entity.Category;
import com.dahai.reggie.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
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
    @DeleteMapping
    public  R<String> delete(long id){
        log.info("删除分类id={}",id);
//        categoryService.removeById(id);
        //重写的删除方法
        categoryService.remove(id);
        return R.success("删除成功");
    }
    @PutMapping
    public  R<String> update(@RequestBody Category category){
        log.info("修改分类信息",category.toString());
        categoryService.updateById(category);
        return R.success("修改成功");
    }


}
