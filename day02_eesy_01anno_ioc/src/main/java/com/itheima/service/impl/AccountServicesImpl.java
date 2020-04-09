package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户的业务层实现类
 *      曾经xml的配置
 *     <bean id="accountService" class="com.itheima.service.impl.AccountServicesImpl"
 *     scope="" init-method="" destroy-method="">
 *          <property name="" value="" / ref=""></>
 *     </bean>
 *
*       用于创建对象的:
 *          它们的作用就和在xml中编写一个<bean>标签实现的功能是一样的
 *          Component
 *              作用：用于把当前类对象存入spring容器中
 *              属性：
 *                  value：用于指定bean的id。当我们不写的时候，默认是当前类名，且首字母小写
 *          Controller：一般用于表现层
 *          Service：一般用在业务层
 *          Repository：一般用于持久层
 *          以上三个注解和Component是一模一样的。
 *          它们三个是spring框架为我们提供明确的三层架构使用的注解，使我们的三层对象更加清晰
 *
 *      用于注入数据的：
 *          它们的作用就和在xml配置文件中的<bean>标签中写一个<property>标签的作用是一样的
 *          Autowired：
*                  自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                  如果IOC容器中没有任何bean的类型和要注入的变量类型匹配，则报错.
 *                  如果IOC容器中有多个类型匹配时
 *                 出现位置：
 *                      可以使变量上，也可以是方法上
 *                  细节：
 *                      在使用注解注入的时候，set方法就不是必须的了
 *          Qualifier:
 *                  作用：在按照类型注入的基础之上再按照 名称 注入。它在给类成员注入时，不能单独使用，
 *                        但是在给方法参数注入时可以
 *                  属性：
 *                       用于指定注入的id
 *          Resource:
 *                  作用：直接按照bean的id注入。它可以独立使用
 *                  属性：
 *                      name:用于指定bean的id
 *          以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现
 *          另外，集合类型的注入只能通过xml来实现
 *
 *          Value：
 *              作用：用于注入基本类型和String类型的数据
 *              属性;
 *                  value:用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式)
 *                          SpEL的写法：${表达式}
 *
 *      用于改变作用范围的：
 *          它们的作用就和在<bean>标签中使用scope属性实现的功能是一样的
 *          Scope:
 *              作用：用于指定bean的作用范围
 *              属性：
 *                  value:指定范围的取值。常用取值： singleton、prototype
 *                          不写的时候，默认情况下是单例的
 *      和生命周期相关的：（了解）
 *          它们的作用就和在<bean>标签中使用init-method和destroy-method属性实现的功能是一样的
 *          PreDestroy
 *              作用：用于指定销毁方法
 *          PostConstruct
 *              作用：用于指定初始化方法
 * @author sugz
 */
@Service("accountServices")
public class AccountServicesImpl implements IAccountService {
    
//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name="accountDao1")
    private IAccountDao accountDao =null;

    @PostConstruct
    public void init() {
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行了");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }

}
