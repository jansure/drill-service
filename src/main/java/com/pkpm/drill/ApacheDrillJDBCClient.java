package com.pkpm.drill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApacheDrillJDBCClient {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DrillUtils.getConnection();
            stmt = conn.createStatement();
            /* Perform a select on data in the classpath storage plugin. */
           //String sql = "select employee_id,first_name,last_name from cp.`employee.json` limit 10";
            String sql = "select * from dfs.`wordcount-result1.txt`";
            rs = stmt.executeQuery(sql);
            System.out.println("EmployeeID" + " "+ "First Name " +"Last Name " + " "+ "Salary");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()) {
                String string = rs.getString(1);
                System.err.println(string);

            }

            DrillUtils.closeConnection(rs,stmt,conn);
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
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
}