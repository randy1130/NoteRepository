package com.dao;

import com.bean.EmpList;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IEmpListDao {

    @Select("select * from emp")
    @Results(id = "resultMap", value = {
            @Result(id = true, property = "empId", column = "EMPNO"),
            @Result(property = "empName", column = "ENAME"),
            @Result(property = "work", column = "JOB"),
            @Result(property = "bossId", column = "MGR"),
            @Result(property = "hireDate", column = "HIREDATE"),
            @Result(property = "sal", column = "SAL"),
            @Result(property = "comm", column = "COMM"),
            @Result(property = "deptId", column = "DEPTNO"),
            /**
             * 基于注解一对一
             */
            @Result(property = "dept", column = "DEPTNO", one = @One(select = "com.dao.IDeptDao.findDeptByDeptId", fetchType = FetchType.LAZY))
    }
    )
    List<EmpList> findAllEmp();
}
