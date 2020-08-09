package com.zr.springboot.repository;


import com.zr.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承JpaRepository来完成对数据库的操作
 * User为实体类，Integer为主键的类型
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
