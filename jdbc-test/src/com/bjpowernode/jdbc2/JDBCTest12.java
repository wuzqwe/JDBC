package com.bjpowernode.jdbc2;

import com.bjpowernode.jdbc2.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            //获取连接
            conn= DBUtil.getConnection();
            //获取预编译的数据库的操作对象
            String sql="select ename from emp where ename like ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"_A%");

            rs=ps.executeQuery();

            while (rs.next())
            {
                System.out.println(rs.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }
}
