package com.bjpowernode.jdbc2;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTest10 {
    public static void main(String[] args) {

        //使用资源绑定器
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rst = null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            //获取预编译数据库操作对象
            String sql = "insert into t_vip(id,name,email) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            ps.setInt(1,7);
            ps.setString(2,"wnagliu");
            ps.setString(3,"wansg@123.com");

            int count =ps.executeUpdate();
            System.out.println(count);
            //处理查询结果集
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
