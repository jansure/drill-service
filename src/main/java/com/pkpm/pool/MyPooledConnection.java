package com.pkpm.pool;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2019/5/15.
 */
@Data
public class MyPooledConnection {

    public Connection connection;
    private  boolean isBusy = false;

    public MyPooledConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public void close (){
        this.isBusy = false;
    }

    public ResultSet query(String sql){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
