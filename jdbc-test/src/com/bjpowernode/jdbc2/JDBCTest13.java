package com.bjpowernode.jdbc2;

import com.bjpowernode.jdbc2.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 修改被锁定的事务
 */
public class JDBCTest13 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;

        try {
            //获取连接
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            //获取预编译的数据库的操作对象
            String sql="update emp set sal=sal*1.1 where job=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            int count=ps.executeUpdate();
            System.out.println(count);
            conn.commit();
        } catch (SQLException e) {
            if(conn!=null)
            {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }
}
