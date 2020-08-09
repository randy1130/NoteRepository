package com.zr.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 声明Security的配置类
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 定制请求的授权规则
         */
        http.authorizeRequests()
                //所有的用户都可以访问/
                .antMatchers("/").permitAll()
                /**
                 *VIP1可以访问level1下的所有，以下相同
                 * 相当于资源和角色的映射
                 */
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        /**
         *开启自动配置的登录功能，如果么有登录，没有授权就会来到登录页面
         * 默认访问/login会来到登录页面
         * 可以自定义登录后访问的页面此处的是请求/userlogin会跳转到login.html
         * 自定义账号的属性名为user，默认为username
         * 自定义密码的属性名为pwd，默认为password
         */
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        /**
         * 开启自动配置的注销功能
         * 注销功能，默认访问/logout注销，清空session
         * logoutSuccessUrl注销完后去的地址
         */
        http.logout().logoutSuccessUrl("/");
        /**
         * 开启记住我的功能,设置记住我的参数
         */
        http.rememberMe().rememberMeParameter("remeber");

    }

    /**
     * 定义认证规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 设置不同的用户名和密码并授予角色信息（可以多角色）
         * 实际的生产中这些的信息要放在数据库中
         */
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123456").roles("VIP1")
                .and()
                .withUser("lisi").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP2", "VIP3");
    }
}