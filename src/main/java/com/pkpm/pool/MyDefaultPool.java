package com.pkpm.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2019/5/15.
 */
public class MyDefaultPool implements IMyPool {

    private final Logger log  = LoggerFactory.getLogger(MyDefaultPool.class);

    private CopyOnWriteArrayList<MyPooledConnection> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private static int initCount;
    private static int step;
    private static int maxCount;

    public MyDefaultPool( ) {
        init();
        //加载驱动
        try {
            Class.forName(DrillConfig.DRILL_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //初始化数据库连接池
        createMyPooledConnection(initCount);

    }

    private void init(){
        jdbcURL = DrillConfig.DRILL_URL;
        jdbcUsername = DrillConfig.DRILL_USERNAME;
        jdbcPassword = DrillConfig.DRILL_PASSWORD;
        initCount = DrillConfig.INIT_COUNT;
        step = DrillConfig.STEP;
        maxCount = DrillConfig.MAX_COUNT;
    }

    @Override
    public MyPooledConnection getMyPooledConnection() {
        if(copyOnWriteArrayList.size() < 1){
            throw new RuntimeException("初始化错误...");
        }
        MyPooledConnection myPooledConnection = null;

        try {
            myPooledConnection = getRealConnectionFromPool();
            while( myPooledConnection == null){
                createMyPooledConnection(step);
                myPooledConnection = getRealConnectionFromPool();
                return myPooledConnection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myPooledConnection;
    }

    private synchronized MyPooledConnection getRealConnectionFromPool() throws SQLException {
        for (MyPooledConnection myPooledConnection : copyOnWriteArrayList) {
            if(!myPooledConnection.isBusy()){
                if(myPooledConnection.getConnection().isValid(3000)){
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                }else{
                    Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                    myPooledConnection.setConnection(conn);
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                }
            }
        }
        return  null;
    }

    @Override
    public void createMyPooledConnection(int count) {
        if( copyOnWriteArrayList.size() > maxCount ||
                copyOnWriteArrayList.size() + count > maxCount){
            throw new RuntimeException("连接池已满。。。");
        }
        
        try {
            for (int i = 0; i < count; i++) {
                Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                MyPooledConnection myPooledConnection = new MyPooledConnection(conn, false);
                copyOnWriteArrayList.add(myPooledConnection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
