package com.bean;

import lombok.Data;

import java.util.List;

@Data
public class DeptList {
    private int deptId;
    private String deptName;
    private String location;
    private List<Emp> list;
}
