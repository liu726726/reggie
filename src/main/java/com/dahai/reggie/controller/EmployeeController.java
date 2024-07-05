package com.dahai.reggie.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahai.reggie.common.R;
import com.dahai.reggie.entity.Employee;
import com.dahai.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        //1.md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据用户名查询数据库
        QueryWrapper<Employee> querywrapper = new QueryWrapper<>();
        querywrapper.eq("username", employee.getUsername());
        Employee emp = employeeService.getOne(querywrapper);
        if(emp == null){
            return R.error("登录失败");
        }
        if (!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if (emp.getStatus()==0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
        public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.save(employee);
        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    public  R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        Page pageInfo = new Page(page,pageSize);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("update_time");
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(empId);
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("更新状态成功");
    }
    @GetMapping("/{id}")
    public  R<Employee> getById(@PathVariable long id){
        log.info("根据id{}查询员工信息...",id);
        Employee one = employeeService.getOne(
                new QueryWrapper<Employee>().eq("id", id)
        );
        if(one != null){
            return R.success(one);
        }
        return R.error("没有查询到对应员工信息");
    }
}

