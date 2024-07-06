package com.dahai.reggie.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dahai.reggie.common.CustomException;
import com.dahai.reggie.entity.Category;
import com.dahai.reggie.entity.Dish;
import com.dahai.reggie.entity.Setmeal;
import com.dahai.reggie.mapper.CategoryMapper;
import com.dahai.reggie.service.CategoryService;
import com.dahai.reggie.service.DishService;
import com.dahai.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishlambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishlambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long dishCount = dishService.count(dishlambdaQueryWrapper);
        if (dishCount > 0) {
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
