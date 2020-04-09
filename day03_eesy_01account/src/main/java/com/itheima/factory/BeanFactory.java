package com.itheima.factory;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service的代理对象工厂
 * @author sugz
 */
public class BeanFactory {
    private IAccountService accountService;

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }
    private TransactionManager txManager;

    public  void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }
    /**
     * 获取Service代理对象
     * @return
     */
    public IAccountService getAccountService(){
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object rtVaule=null;
                        try{
                            //开启事务
                            txManager.beginTransaction1();
                            //执行操作
                            rtVaule=method.invoke(accountService,args);
                            //提交事务
                            txManager.commit();
                            //返回结果
                            return rtVaule;
                        }catch (Exception e){
                            //回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //释放连接
                            txManager.release();
                        }
                    }
                });
    }
}
