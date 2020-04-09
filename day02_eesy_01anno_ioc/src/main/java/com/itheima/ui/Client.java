package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 * @author sugz
 */
public class Client {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
//        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        IAccountService as=ac.getBean("accountServices",IAccountService.class);
//        System.out.println(as);
//
//        IAccountDao adao=ac.getBean("accountDao",IAccountDao.class);
//        System.out.println(adao);

        as.saveAccount();
        ac.close();
    }
}
