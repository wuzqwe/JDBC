package com.bjpowernode.jdbc2;

import com.bjpowernode.jdbc2.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个程序开启一个事务，这个事务专门进行查询，使用行级锁、悲观锁，锁住相关的记录 for update
 */
public class JDBCTest14 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            //获取连接
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            //获取预编译的数据库的操作对象
            String sql="select ename,job,sal from emp where job=? for update ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");

            rs=ps.executeQuery();

            while (rs.next())
            {
                System.out.println(rs.getString("ename")+","+rs.getString("job")+","+rs.getString("sal"));
            }
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
            DBUtil.close(conn,ps,rs);
        }
    }
}
