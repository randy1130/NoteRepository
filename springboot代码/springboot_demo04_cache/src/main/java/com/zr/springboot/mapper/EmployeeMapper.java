package com.zr.springboot.mapper;

import com.zr.springboot.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper {

    @Select("SELECT * from employee WHERE id=#{id}")
    Employee getEmpById(Integer id);

    @Select("SELECT * from employee WHERE lastName=#{lastName}")
    Employee getEmpByLastName(String lastName);

    @Update("UPDATE employee SET lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} WHERE id=#{id}")
    void updateEmp(Employee employee);

    @Delete("DELETE from employee WHERE id=#{id}")
    void deleteEmp(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) VALUES (#{lastName},#{email},#{gender},#{dId})")
    void insertEmp(Employee employee);
}
