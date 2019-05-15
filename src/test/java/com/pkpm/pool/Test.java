package com.pkpm.pool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2019/5/15.
 */
public class Test {

    public static IMyPool mypool = MyPoolFactory.getInstance();

    public static void main(String[] args) {
        MyPooledConnection myPooledConnection = mypool.getMyPooledConnection();
        ResultSet result = myPooledConnection.query("select * from User");

        try {
            while(result.next()){
                System.out.println("-----" + result.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
