package com.pkpm.pool;

/**
 * Created by Administrator on 2019/5/15.
 */
public interface IMyPool {

     MyPooledConnection getMyPooledConnection();

     void createMyPooledConnection(int count);
}
