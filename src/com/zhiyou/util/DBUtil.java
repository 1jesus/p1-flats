package com.zhiyou.util;

import java.sql.*;

/**
 * @Classname DBUtil
 * @Date 2021/9/9 16:18
 * <p>
 * 负责连接数据库,关流操作,把重复的操作抽成方法
 */
public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/apartment info ms";
    private static String username = "root";
    private static String password = "445566";

    //加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常");
            e.printStackTrace();
        }
    }

    //获得连接对象
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            System.out.println("获得连接对象异常");
            throwables.printStackTrace();
        }
        return connection;
    }

    //关流
    public static void close(ResultSet resultSet,Statement statement,Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void close(Statement statement,Connection connection){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
