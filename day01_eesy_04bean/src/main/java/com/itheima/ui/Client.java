package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 * @author sugz
 */
public class Client {
    /**
     *  @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
//        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        as.saveAccount();

        //手动关闭容器
        ac.close();
    }
}
