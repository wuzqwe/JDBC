package com.bjpowernode.jdbc2;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
//用户名：
//fdsa
//密码：
//fdsa' or '1'='1
//登录成功
//这种方法被称为SQL注入。（黑客经常使用）
public class JDBCTest07 {
    public static void main(String[] args) {

        //初始化界面
        Map<String,String> userLoginInfo=initUI();
        //验证用户名和密码
        boolean loginSuccess=login(userLoginInfo);
        //最后输出结果
        System.out.println(loginSuccess?"登录成功":"登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {

        boolean loginSuccess=false;

        String LoginName=userLoginInfo.get("loginName");
        String LoginPwd=userLoginInfo.get("loginPwd");
        //使用资源绑定器
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
        String driver=bundle.getString("driver");
        String url=bundle.getString("url");
        String user=bundle.getString("user");
        String password=bundle.getString("password");

        Connection conn=null;
        Statement stat=null;
        ResultSet rst=null;
        try {
            //注册驱动动
            Class.forName(driver);
            //获取数据库连接
            conn=DriverManager.getConnection(url,user,password);
            //获取操作数据库的对象
             stat = conn.createStatement();
             //执行Sql语句
            String sql="select * from t_user1 where loginName = '" + LoginName + "'and loginPwd='" + LoginPwd + "'";
            rst= stat.executeQuery(sql);
            //处理查询结果集
            if(rst.next())
            {
                loginSuccess=true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rst!=null)
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

       return  loginSuccess;
    }

    /**
     * 初始化界面
     * @return 用户输入的用户名和密码等登录信息
     */
    private static Map<String, String> initUI() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("用户名：");
        String LoginName=scanner.nextLine();
        System.out.println("密码：");
        String LoginPwd=scanner.nextLine();

        Map<String,String> userLoginInfo=new HashMap<>();
        userLoginInfo.put("loginName",LoginName);
        userLoginInfo.put("loginPwd",LoginPwd);

        return userLoginInfo;
    }


}
