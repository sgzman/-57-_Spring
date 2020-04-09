package com.itheima.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件
 *      JavaBean > 实体类
 *
 *    他就是创建我们的service和dao对象的
 *
 *    第一个：需要一个配置文件来配置我们的service和dao
 *              配置的内容：唯一标识=全限定类名（key=value）
 *    第二个：通过读取配置文件中的内容，反射创建对象
 *
 *    我的配置文件可以是xml也可以是properties
 * @author sugz
 */
public class BeanFactory {
    /**
     *定义一个Properties对象
     */
    private static Properties props;

    /**
     * 定义一个Map，用于存放我们要创建的对象，我们把它称之为容器
     */
    private static Map<String,Object> beans;

    //使用静态代码块为Properties对象赋值(静态代码块只在类加载的时候执行一次)
    static {
        try{
            //实例化对象
            props=new Properties();
            //获取Properties文件的流对象
            InputStream in=BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans=new HashMap<String,Object>();
            //取出配置文件中所有的key
            Enumeration keys=props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key=keys.nextElement().toString();
                //根据key获取value
                String beanPath=props.getProperty(key);
                //反射创建对象
                Object value=Class.forName(beanPath).getDeclaredConstructor().newInstance();
                //把key和value存入容器之中
                beans.put(key,value);
            }

        }catch (Exception e){
            throw new ExceptionInInitializerError("初始化Properties失败！");
        }

    }

    /**
     * 根据bean的名称获取对象，此时已经是单例的了
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return  beans.get(beanName);
    }


    /**
     * 根据bean的名称，获取bean对象
     * @return

    public static Object getBean(String beanName){
        Object bean=null;
        try{
            //通过beanName获取全限定类名
          String beanPath=props.getProperty(beanName);
          //此种方法Java9之后废弃
          //bean=Class.forName(beanPath).newInstance();
           bean= Class.forName(beanPath).getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  bean;
    }
     */
}
