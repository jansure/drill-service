package com.pkpm;

import java.sql.*;

public class ApacheDrillJDBCClientTest {

    static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
    //static final String DB_URL = "jdbc:drill:zk=114.115.128.169:2181";
    static final String DB_URL = "jdbc:drill:zk=s5:2181,s6:2181,s7:2181";

    static final String USER = "admin";
    static final String PASS = "admin";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();
            /* Perform a select on data in the classpath storage plugin. */
           //String sql = "select employee_id,first_name,last_name from cp.`employee.json` limit 10";
            String sql = "select * from dfs.`wordcount-result1.txt`";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("EmployeeID" + " "+ "First Name " +"Last Name " + " "+ "Salary");
            System.out.println("-------------------------------------------------------------");
            while(rs.next()) {
                /*int employeeId  = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.err.println(employeeId+ ":  "+ firstName+ " :  "+ lastName );*/
                //System.err.println(rs.getObject(0) );
                String string = rs.getString(1);
                System.err.println(string);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}