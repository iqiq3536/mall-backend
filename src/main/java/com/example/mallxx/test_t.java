package com.example.mallxx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test_t {
    public static void main(String[] args) {
        /*String url = "jdbc:mysql://localhost:3306/mall";
        String user = "root";
        String password = "123456";*/
        String url = "rm-cn-lyi429m9t00096xo.rwlb.rds.aliyuncs.com";
        String user = "root";
        String password = "123456";

        try {
            // 加载MySQL JDBC驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 建立连接
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功！");

            // 关闭连接
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL JDBC驱动");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }
}
