package com.zr.springboot;

import com.zr.springboot.bean.Employee;
import com.zr.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemo04CacheApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    //将配合的类注入
    @Autowired
    private RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void test01() {
        //调用类存入对象redis数据库
        empRedisTemplate.opsForValue().set("employee", employeeMapper.getEmpById(1));
    }


    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().append("docker", "zhangjuanjian");
        // System.out.println(stringRedisTemplate.opsForValue().get("docker"));
    }

}
