package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都是在业务层的
 * @author sugz
 */
public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {

        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try{
            //开启事务
            txManager.beginTransaction1();
            //执行操作
            List<Account> accounts= accountDao.findAllAccount();
            //提交事务
            txManager.commit();
            //返回结果
            return accounts;
        }catch (Exception e){
            //回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();
        }

    }

    @Override
    public Account findAccountById(Integer accountId) {
        try{
            //开启事务
            txManager.beginTransaction1();
            //执行操作
            Account account= accountDao.findAccountById(accountId);
            //提交事务
            txManager.commit();
            //返回结果
            return account;
        }catch (Exception e){
            //回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            //开启事务
            txManager.beginTransaction1();
            //执行操作
            accountDao.saveAccount(account);
            //提交事务
            txManager.commit();

        }catch (Exception e){
            //回滚操作
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try{
            //开启事务
            txManager.beginTransaction1();
            //执行操作
            accountDao.updateAccount(account);
            //提交事务
            txManager.commit();
            //返回结果
        }catch (Exception e){
            //回滚操作
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try{
            //开启事务
            txManager.beginTransaction1();
            //执行操作
            accountDao.deleteAccount(accountId);
            //提交事务
            txManager.commit();
        }catch (Exception e){
            //回滚操作
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {

        try{
            //1.开启事务
            txManager.beginTransaction1();
            //2.执行操作

            //2.1.根据名称查询转出账户
            Account source=accountDao.finAccountByName(sourceName);
            //2.2.根据名称查询转入账户
            Account target=accountDao.finAccountByName(targetName);
            //2.3.转出账户减钱
            source.setMoney(source.getMoney()-money);
            //2.4.转入账户加钱
            target.setMoney(target.getMoney()+money);
            //2.5.更新转出账户
            accountDao.updateAccount(source);

            int i=1/0;

            //2.6.更新转入账户
            accountDao.updateAccount(target);
            //3.提交事务
            txManager.commit();
        }catch (Exception e){
            //4.回滚操作
            txManager.rollback();
            e.printStackTrace();
        }finally {
            //5.释放连接
            txManager.release();
        }


    }
}
