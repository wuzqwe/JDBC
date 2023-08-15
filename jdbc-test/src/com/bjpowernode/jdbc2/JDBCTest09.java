package com.bjpowernode.jdbc2;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTest09 {
    public static void main(String[] args)  {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入desc或asc,desc表示降序,asc表示升序");
        System.out.println("请输入：");
        String keyword=scanner.nextLine();

        //使用资源绑定器
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
        String driver=bundle.getString("driver");
        String url =bundle.getString("url");
        String user=bundle.getString("user");
        String password=bundle.getString("password");

       /* Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rst=null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            conn= DriverManager.getConnection(url,user,password);
            //获取预编译数据库操作对象
            String sql="select ename from emp order by ename"+keyword;
             ps = conn.prepareStatement(sql);
             //执行sql语句
             rst = ps.executeQuery();
            //处理查询结果集
            while (rst.next())
            {
                String ename=rst.getString("ename");
                System.out.println(ename);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(rst!=null)
            {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null)
            {
                try {
                    ps.close();
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
        }*/


        Connection conn=null;
         Statement stat=null;
        ResultSet rst=null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            conn= DriverManager.getConnection(url,user,password);
            //获取数据库操作对象
             stat= conn.createStatement();
            //执行sql语句
              String sql="select ename from emp order by ename "+keyword;
            rst = stat.executeQuery(sql);
            //处理查询结果集
            while (rst.next())
            {
                String ename=rst.getString("ename");
                System.out.println(ename);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(rst!=null)
            {
                try {
                    rst.close();
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
