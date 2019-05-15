package com.pkpm.pool;

/**
 * Created by Administrator on 2019/5/15.
 */
public class MyPoolFactory {

    public static class CreatePool{
        public static IMyPool pool = new MyDefaultPool();
    }

    public static IMyPool getInstance(){
        return CreatePool.pool;
    }
}
