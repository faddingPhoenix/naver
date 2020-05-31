package com.naver.test.problem.two;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataProcessor implements Runnable{

    private List<String> cache = new ArrayList<String>();

    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    public void run() {
        ReentrantReadWriteLock.ReadLock readLock = null;
        ReentrantReadWriteLock.WriteLock writeLock = null;
        try {
            // 1.get readlock
            readLock = LOCK.readLock();
            readLock.lock();

            // 2.check and update
            if(cache.isEmpty()){
                // 2.1 release read lock
                readLock.unlock();

                // 2.2 get writelock before write
                writeLock = LOCK.writeLock();
                writeLock.lock();

                String[] datas = Datasource.getData();
                for (String data : datas) {
                    cache.add(data);
                }
                // 2.3 write lock downgrading
                writeLock.unlock();
                writeLock = null;
                readLock.lock();
            }

            // 3.process the data
            for (String data : cache) {
                System.out.println(data);
            }
        }finally {
            if(readLock != null){
                readLock.unlock();
            }
            if(writeLock != null){
                writeLock.unlock();
            }
        }
    }
}
