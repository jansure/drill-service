package com.pkpm;

import org.junit.Test;

/**
 * Created by Administrator on 2019/3/27.
 */
public class TestDrill  {

    @Test
    public void test1(){
        tableSizeFor(100);
    }
    static final int MAXIMUM_CAPACITY = 1 << 30;

    int tableSizeFor(int cap) {
        int n = cap - 1;
        System.err.println(n);
        n |= n >>> 1;
        System.err.println(n);
        n |= n >>> 2;
        System.err.println(n);
        n |= n >>> 4;
        System.err.println(n);
        n |= n >>> 8;
        System.err.println(n);
        n |= n >>> 16;
        System.err.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
