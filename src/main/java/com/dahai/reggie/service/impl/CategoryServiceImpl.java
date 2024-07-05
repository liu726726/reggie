package com.dahai.reggie.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dahai.reggie.entity.Category;
import com.dahai.reggie.mapper.CategoryMapper;
import com.dahai.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
