package com.zr.springboot.controller;


import com.zr.springboot.bean.Department;
import com.zr.springboot.mapper.IDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private IDeptMapper deptMapper;

    @GetMapping("/dept/{id}")
    public Department checkById(@PathVariable("id") Integer id) {
        return deptMapper.checkById(id);
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department) {
        deptMapper.insertDepartment(department);
        return department;
    }

}
