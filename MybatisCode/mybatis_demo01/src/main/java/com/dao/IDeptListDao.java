package com.dao;

import com.bean.DeptList;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IDeptListDao {


    @Select("select * from dept")
    @Results(id = "resultMap", value = {
            @Result(property = "deptId", column = "DEPTNO"),
            @Result(property = "deptName", column = "DNAME"),
            @Result(property = "location", column = "LOC"),
            @Result(property = "list", column = "DEPTNO", many = @Many(select = "com.dao.IEmpDao.findEmpByDeptId", fetchType = FetchType.LAZY))
    })
    List<DeptList> findAllDept();
}
