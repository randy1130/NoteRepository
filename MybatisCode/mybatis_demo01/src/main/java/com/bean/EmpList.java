package com.bean;

import lombok.Data;

import java.util.Date;

@Data
public class EmpList {

    private int empId;
    private String empName;
    private String work;
    private int bossId;
    private Date hireDate;
    private double sal;
    private double comm;
    private int deptId;
    private Dept dept;
}
