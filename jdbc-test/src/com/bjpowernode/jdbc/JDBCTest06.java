package com.bjpowernode.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest06 {
    public static void main(String[] args) {
        //使用资源绑定器
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
        String driver=bundle.getString("driver");
        String url=bundle.getString("url");
        String user=bundle.getString("user");
        String password=bundle.getString("password");

        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            conn=DriverManager.getConnection(url,user,password);
            //获取数据库的对象
            stat = conn.createStatement();
            //执行SQL语句
//            String sql="insert into t_vip(id,name,email) values(6,'bs','bs@123.com')";
            String sql1="select id,name,email from t_vip";
//            int count = stat.executeUpdate(sql);
//            System.out.printf(count==1?"插入成功":"插入失败");
             rs = stat.executeQuery(sql1);
            //处理查询结果集
            while (rs.next())
            {
                String id=rs.getString("id");
                String name=rs.getString("name");
                String email=rs.getString("email");
                System.out.println(id+","+name+","+email);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stat!=null)
            {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
