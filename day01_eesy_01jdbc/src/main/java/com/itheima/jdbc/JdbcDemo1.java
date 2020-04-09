package com.itheima.jdbc;

import java.sql.*;

/**
 * 程序的耦合
 *      耦合：程序间的依赖关系
 *          包括：
 *              类之间的依赖
 *              方法间的依赖
 *      解耦：降低程序间的依赖
 *      实际开发中：
 *          应该做到，编译器不依赖，运行时才依赖
 *      解耦的思路：
 *          第一步：使用反射来创建对象，避免使用new关键字
 *          第二步：通过读取配置文件，来获取要创建的对象的全限定类名
 *
 * @author sugz
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //注册驱动
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy?serverTimezone=UTC","root","root");
        //获取操作数据库的预处理对象
        PreparedStatement pstm=conn.prepareStatement("select * from account");
        //执行SQL语句  --得到结果集
        ResultSet rs=pstm.executeQuery();
        //遍历结果集
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
        //释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
