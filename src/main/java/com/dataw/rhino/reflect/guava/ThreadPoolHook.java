package com.dataw.rhino.reflect.guava;

import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Kinyi_Chan
 * @since 2021-02-02
 */
public class ThreadPoolHook {

    static class ListenableThreadpool extends AbstractListeningExecutorService {

        private final ExecutorService delegate;

        public ListenableThreadpool(ExecutorService delegate) {
            this.delegate = delegate;
        }

        @Override
        public void shutdown() {
            delegate.shutdown();
        }

        @Override
        public List<Runnable> shutdownNow() {
            return delegate.shutdownNow();
        }

        @Override
        public boolean isShutdown() {
            return delegate.isShutdown();
        }

        @Override
        public boolean isTerminated() {
            return delegate.isTerminated();
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return delegate.awaitTermination(timeout, unit);
        }

        @Override
        public void execute(Runnable command) {
            delegate.execute(command);
        }

        @Override
        public <T> ListenableFuture<T> submit(Callable<T> task) {
            if (task == null) {
                throw new NullPointerException();
            }
            ListenableFutureTask<T> ftask = ListenableFutureTask.create(task);
            delegate.execute(ftask);
            return ftask;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            Thread.sleep(1000 * 5);
            return "success";
        };
        /*
        同步阻塞等待future结果或者一直轮询结果状态

        Future<String> future = executorService.submit(callable);
//        System.out.println(future.get());

        while (!future.isDone()) {
            Thread.sleep(1000);
            System.out.println("no result, sleep 1s");
        }
        System.out.println(future.get());
        */

        //使用预留的done()方法实现钩子回调
        ListeningExecutorService listeningExecutorService = MoreExecutors
                .listeningDecorator(new ListenableThreadpool(executorService));
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(callable);
        listenableFuture.addListener(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "-我先完成了, 输出的值是: " + listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, executorService);
        listenableFuture.addListener(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "-我也完成了, 输出的值是: " + listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, executorService);
        while (true) {
            Thread.sleep(1000);
            System.out.println("每一秒我都在做自己的事呢哈哈哈哈");
        }
    }
}
