package com.dahai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dahai.reggie.entity.Category;

import java.io.Serializable;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
