package com.pkpm.drill;

import java.sql.*;

/**
 * Created by Administrator on 2019/5/14.
 */
public class DrillUtils {

    static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
    //static final String DB_URL = "jdbc:drill:zk=114.115.128.169:2181";
    static final String DB_URL = "jdbc:drill:zk=s5:2181,s6:2181,s7:2181";
    static final String USER = "admin";
    static final String PASS = "admin";

    public DrillUtils() {
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try
        {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        //返回连接结果
        return conn;
    }

    /**
     * 关闭连接  包含3个参数
     * @param rs  rs对象
     * @param stmt Statement对象
     * @param conn 连接对象
     * @throws SQLException
     */
    public static void closeConnection(ResultSet rs, Statement stmt ,Connection conn) throws SQLException {
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}


