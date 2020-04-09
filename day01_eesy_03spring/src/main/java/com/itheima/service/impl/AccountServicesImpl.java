package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountImpl;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 * @author sugz
 */
public class AccountServicesImpl implements IAccountService {

    private IAccountDao accountDao=new AccountImpl();

    public AccountServicesImpl(){
        System.out.println("对象创建了");
    }
    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
