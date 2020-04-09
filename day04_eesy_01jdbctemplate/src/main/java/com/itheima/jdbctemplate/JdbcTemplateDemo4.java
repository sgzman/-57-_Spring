package com.itheima.jdbctemplate;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate的最基本用法
 * @author sugz
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        IAccountDao accountDao=ac.getBean("accountDao",IAccountDao.class);
        Account account=accountDao.finAccountById(1);
        System.out.println(account);
        //剩余的方法自己测试
    }
}
