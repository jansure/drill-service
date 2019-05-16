package com.pkpm.pool;

/**
 * Created by Administrator on 2019/5/15.
 */
public class DrillConfig {

     public static final  String DRILL_Driver = "org.apache.drill.jdbc.Driver";
     public static final  String DRILL_URL = "jdbc:drill:zk=s5:2181,s6:2181,s7:2181";
     public static final  String DRILL_USERNAME = "admin";
     public static final  String DRILL_PASSWORD = "admin";


     public static final  int INIT_COUNT = 10;
     public static final  int  STEP = 2;
     public static final  int  MAX_COUNT = 50;
}
