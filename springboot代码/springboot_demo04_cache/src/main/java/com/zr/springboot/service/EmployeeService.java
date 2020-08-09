package com.zr.springboot.service;

import com.zr.springboot.bean.Employee;
import com.zr.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @CacheConfig 抽取缓存的公共配置
 * cacheNames = "emp"为每个缓存都有的
 */
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @Cacheable 注解
     * 标注缓存的方法 缓存中的是key和value的键值对
     * 默认key为传过来的参数（此处为id），value为
     * 返回的对象（此处为employee）
     * cacheNames指定缓存的区域名称
     * condition（符合缓存）后面的为判断，当id>1是为true才会缓存
     * unless（否定缓存）后面的也是判断，当id==2是false是才缓存
     * 流程：请求过来先去缓存中查如果有：就直接返回结果。如果没有：
     * 就去数据库中查，查到之后放入到缓存中，然后把结果返回
     */
    @Cacheable(cacheNames = {"emp"}, condition = "#id>1", unless = "#id==2")
    public Employee getEmpById(Integer id) {
        Employee employee = employeeMapper.getEmpById(id);
        System.out.println("查了" + id + "号员工");
        return employee;
    }

    /**
     * @CachePut 注解
     * 流程：先去执行方法，去查数据库，修改数据库的值，
     * 然后将修改后的值查出来，放入到缓存区中。
     * key = "#employee.id" 设置缓存的key为参数employee中的id
     */
    @CachePut(cacheNames = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /***
     * @CacheEvict 清除缓存
     * allEntries = true 默认为false,当为true的时候会清空所有的缓存
     * beforeInvocation = true 默认为false，当为true的时候会在此方法
     * 之前清除缓存，（false的话会在方法执行之后清除缓存）（在方法中有
     * 异常的时候区别明显）
     */
    @CacheEvict(cacheNames = "emp", key = "#id")
    public void deleteEmp(Integer id) {
        //employeeMapper.deleteEmp(id);
        //int i = 10 / 0;
        System.out.println("清除缓存执行了");
    }

    /**
     * @Caching 组合注解
     * 用lastName查完数据库后，按照key=lastName把查询结果Employee对象存进去缓存
     * 然后方法执行完，再把返回值的结果Employee对象的email属性和id属性当做key，在缓存中在存
     * 入Employee对象。(后续在用上述的方法查的时候，缓存中就已存在了id和email对应的同一个对象)
     * 注意:此处的result代表的为返回值对象Employee实例
     */
    @Caching(
            cacheable =
                    {@Cacheable(cacheNames = "emp", key = "#lastName")},
            put =
                    {@CachePut(cacheNames = "emp", key = "#result.email"),
                            @CachePut(cacheNames = "emp", key = "#result.id")
                    })
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }


}
