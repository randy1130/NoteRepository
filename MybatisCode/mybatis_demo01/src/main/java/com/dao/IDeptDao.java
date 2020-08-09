package com.dao;

import com.bean.Dept;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface IDeptDao {

    @Select("select * from dept where DEPTNO=#{deptId}")
    @Results(id = "resultMap", value = {
            @Result(property = "deptId", column = "DEPTNO"),
            @Result(property = "deptName", column = "DNAME"),
            @Result(property = "location", column = "LOC"),
    })
    Dept findDeptByDeptId(Integer deptId);

 }
