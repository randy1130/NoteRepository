package com.zr.springboot.mapper;


import com.zr.springboot.bean.Department;
import org.apache.ibatis.annotations.*;


public interface IDeptMapper {
    @Select("select * from department where id=#{id}")
    Department checkById(Integer id);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(departmentName) VALUES (#{departmentName})")
    int insertDepartment(Department department);

    @Delete("DELETE FROM department WHERE id=#{id}")
    int deleteById(Integer id);

    @Update("update department set departmentName=#{departmentName} WHERE id=#{id}")
    int updateDepartment(Department department);


}
