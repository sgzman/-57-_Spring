package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 * @author sugz
 */
public class AccountServicesImpl implements IAccountService {

    //private IAccountDao accountDao=new AccountImpl();
    //private IAccountDao accountDao= (IAccountDao) BeanFactory.getBean("accountDao");
    int i=1;

    @Override
    public void saveAccount() {
        IAccountDao accountDao= (IAccountDao) BeanFactory.getBean("accountDao");
        System.out.println("accountDao是： "+accountDao);
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
