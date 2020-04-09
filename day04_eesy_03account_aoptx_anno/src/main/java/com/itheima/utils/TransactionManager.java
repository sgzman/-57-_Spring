package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 * @author sugz
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.*.*(..))")
    private void pt1(){

    }
    /**
     * 开启事务
     */
//    @Before("pt1()")
    public void beginTransaction1(){
        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("pt1()")
    public void commit(){
        try{
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("pt1()")
    public void rollback(){
        try{
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 释放事务
     */
//    @After("pt1()")
    public void release(){
        try{
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();//将线程上绑定的连接进行解绑操作
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * @return
     */
    @Around("pt1()")
    public Object around(ProceedingJoinPoint pjp){
        Object rtValue=null;
        try{
            //1.获取数组
            Object[] args=pjp.getArgs();
            //2.开启事务
            this.beginTransaction1();
            //3.执行方法
            rtValue= pjp.proceed(args);
            //4.提交事务
            this.commit();
            return rtValue;
        }catch (Throwable t){
            this.rollback();
            throw new RuntimeException(t);
        }finally {
            this.release();
        }
    }

}
