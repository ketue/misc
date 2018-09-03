package com.monical.jdk.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zijie.cao
 * @date 2018-08-17 11:06:59
 */
public class DealThreadPoolExecutor extends ThreadPoolExecutor {

    public DealThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

        if (t != null) {
            System.out.println("r = [" + r + "], t = [" + t + "]");
        }
    }
}


class DealThreadPoolExecutorTest {
    public static void main(String[] args) {
        DealThreadPoolExecutor poolExecutor = new DealThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 0; i < 100; i++) {

            final int j = i;
            poolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + j);
                if (j % 19 == 0) {
                    throw new RuntimeException("j");
                }
            });
        }
    }
}
