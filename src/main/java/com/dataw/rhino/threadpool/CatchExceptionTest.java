package com.dataw.rhino.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Kinyi_Chan
 * @since 2021-02-19
 */
public class CatchExceptionTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Object> future = service.submit(() -> {
            throw new RuntimeException("this is my exception");
        });
        //这行代码已经抛异常了，下面代码无效，应该使用UncaughtExceptionHandler来捕获异常
        Object x = null;
        try {
            x = future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }


        if (x instanceof Throwable) {
            String message = ((Throwable) x).getMessage();
            System.out.println(message);
        }
//        System.out.println(x);
        else {
            System.out.println("normal");
        }
    }
}
