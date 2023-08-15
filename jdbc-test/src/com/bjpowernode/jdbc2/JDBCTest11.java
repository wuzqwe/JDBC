package com.bjpowernode.jdbc2;

import java.sql.*;
import java.util.ResourceBundle;
//将jdbc事务自动提交机制改为手动提交
public class JDBCTest11 {
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
            //将自动提交改为手动提交
            conn.setAutoCommit(false);//开始事务
            //获取预编译数据库操作对象
            String sql = "update t_act set balance = ? where actno=?";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count =ps.executeUpdate();

            //假设中间出现问题
//            String s=null;
//            s.toString();

            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count +=ps.executeUpdate();

            System.out.println(count==2?"转账成功":"转账失败");

            //程序能走到这里说明没有异常，事务结束，手动提交
            conn.commit();//提交事务
            //处理查询结果集


        } catch (Exception e) {
//        e.printStackTrace();
            //遇到异常必须回滚
            if(conn!=null)
            {
                try {
                    conn.rollback();//回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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
