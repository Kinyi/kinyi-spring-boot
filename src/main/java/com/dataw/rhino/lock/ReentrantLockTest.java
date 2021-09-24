package com.dataw.rhino.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Kinyi_Chan
 * @since 2021-02-06
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        try {
            writeLock.lock();

            System.out.println("writelock lock");
            readLock.lock();

            System.out.println("readlock lock");


        } finally {


            readLock.unlock();

            System.out.println("readlock unlock");
            writeLock.unlock();

            System.out.println("writelock unlock");
        }
    }
}
