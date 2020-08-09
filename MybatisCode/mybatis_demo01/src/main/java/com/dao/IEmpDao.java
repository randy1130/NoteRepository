package com.dao;

import com.bean.Emp;
import com.bean.EmpList;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IEmpDao {


    @Select("select * from emp where DEPTNO=#{deptId}")
    @Results(id = "resultMap", value = {
            @Result(id = true, property = "empId", column = "EMPNO"),
            @Result(property = "empName", column = "ENAME"),
            @Result(property = "work", column = "JOB"),
            @Result(property = "bossId", column = "MGR"),
            @Result(property = "hireDate", column = "HIREDATE"),
            @Result(property = "sal", column = "SAL"),
            @Result(property = "comm", column = "COMM"),
            @Result(property = "deptId", column = "DEPTNO"),
    }
    )
    List<Emp> findEmpByDeptId(Integer deptId);
}
