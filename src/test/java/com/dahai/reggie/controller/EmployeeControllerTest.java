package com.dahai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dahai.reggie.BaseTest;
import com.dahai.reggie.entity.Employee;
import com.dahai.reggie.service.EmployeeService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

public class EmployeeControllerTest extends BaseTest {
    @Autowired
    private EmployeeService employeeService;
    @Test
    void getUserTest() {
        Employee employee = new Employee();
        employee.setUsername("admin");
        employee.setPassword("admin");
        QueryWrapper<Employee> querywrapper = new QueryWrapper<>();
        querywrapper.eq("username", employee.getUsername());
        Employee emp = employeeService.getOne(querywrapper);
        System.out.println(emp);
    }
    @Test
    void md5Test(){
        String password = "123456";
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
    }
    @Test
    void getpage(){
        String password = "123456";
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
    }
}
