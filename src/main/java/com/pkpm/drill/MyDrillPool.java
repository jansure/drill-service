package com.pkpm.drill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2019/5/14.
 * https://www.cnblogs.com/jameszbp/p/4490727.html
 */
public class MyDrillPool {


    protected static final Logger logger = LoggerFactory.getLogger(MyDrillPool.class);

    private LinkedList<Connection> connPool=new LinkedList<Connection>();
    /**
     * 读写锁
     */
    private final StampedLock stampedLock = new StampedLock();

    public MyDrillPool(){
        for(int i = 0 ;i < 10; i++ ){
            this.connPool.addLast(this.CreateConnection());
        }
    }

    //在获取数据库连接的时候，如果是多个线程并发的获取的话，当连接池中没有连接的时候，那么这候再获取的时候就会报错，而且该操作属于非线程安全的
    public Connection CreateConnection(){
        long stamp = stampedLock.tryOptimisticRead();
        //检查乐观读锁后是否有其他写锁发生，有则返回false
        if( !stampedLock.validate(stamp) ){
            //获取一个悲观读锁
            stamp = stampedLock.readLock();
            try{
                if( this.connPool.size() > 0 ){
                    return DrillUtils.getConnection();
                }

            }finally {
                stampedLock.unlock(stamp);
            }
        }
        /**
         * long ws = stampedLock.tryConvertToWriteLock(stamp); //读锁转换为写锁
         if (ws != 0L) { //转换成功

         stamp = ws; //票据更新
         x = newX;
         y = newY;
         break;
         } else {
         stampedLock.unlockRead(stamp); //转换失败释放读锁
         stamp = stampedLock.writeLock(); //强制获取写锁
         }

         作者：knock_小新
         链接：https://juejin.im/post/5bacf523f265da0a951ee418
         来源：掘金
         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
          */

        return DrillUtils.getConnection();
    }

    public Connection GetConnection(){
        return connPool.removeFirst();
    }

    public void FreeConnection(Connection conn){
        this.connPool.addLast(conn);
    }


    /**
     *  synchronized(connectionsPool){
     if(this.connectionsPool.size()>0){
     return JdbcUtil.getConnection();
     }
     if(this.currentCount<MaxCount){
     this.currentCount++;
     return this.CreateConnection();
     }
     }
     */

}
