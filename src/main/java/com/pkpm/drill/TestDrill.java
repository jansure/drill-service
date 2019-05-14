/*
package com.pkpm.drill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

*/
/**
 * Created by Administrator on 2019/3/19.
 *//*

public class TestDrill {


   */
/* public static void main(String[] args) throws Exception {

        Class.forName("org.apache.drill.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:drill:zk=s5:2181");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM cp.`employee.json` LIMIT 20");
        while(rs.next()){
            System.err.println(rs.getString(1));
        }
    }*//*



    */
/*
      * Use this if you want zookeeper to start drill automatically.
      *//*

    public static final String DRILL_JDBC_LOCAL_URI = "jdbc:drill:zk=local";

	*/
/*
	 * Use this if drill is running.
	 *//*

     public static final String DRILL_JDBC_LOCAL_URI = "jdbc:drill:drillbit=192.xxx.xxx.xxx";

    public static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";

    public static void main(String[] args) throws Exception {

        try {
            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }

        try (Connection conn = new Driver().connect(DRILL_JDBC_LOCAL_URI, null);
             Statement stmt = conn.createStatement();) {

            String sql = "select employee_id,first_name,last_name from cp.`employee.json` limit 10";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getInt("employee_id") + "\t");
                System.out.print(rs.getString("first_name") + "\t");
                System.out.print(rs.getString("last_name") + "\t");
                System.out.println();
            }
            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
*/
