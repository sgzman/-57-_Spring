package com.itheima.utils;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 * @author sugz
 */
public class Logger {

    /**
     * 用于打印日志，并且计划让其在切入点方法执行前执行（切入点方法就是我们的业务方法）
     */
    public void printLog(){
        System.out.println("Logger类中的printLog方法开始记录日志了。");
    }
}
