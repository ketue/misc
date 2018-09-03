package com.monical.misc;

import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zijie.cao
 * @date 2018-08-13 23:33:36
 */
public class DelayTaskTest {

    private final DelayQueue<DelayTask> queue = new DelayQueue<>();

    private static ScheduledExecutorService scheduledExecutorService = Executors.
            newScheduledThreadPool(8, new ThreadFactoryBuilder().setNameFormat("DelayTaskTest-%d").build());

    public void build() {
        for (int i = 0; i < 10; i += 3) {
            queue.add(new DelayTask("thread" + i, 2, TimeUnit.SECONDS));
        }

        System.out.println("queue put done");

        while (!queue.isEmpty()) {
            try {
                DelayTask task = queue.take();
                System.out.println(task.name + ":" + System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method1() {
        queue.add(new DelayTask("thread", 2, TimeUnit.SECONDS));
        // scheduledExecutorService.schedule(new TicketGenerator(23, System.currentTimeMillis()), 2, TimeUnit.SECONDS);
        Random random = new Random();
        int num = random.nextInt(9999);

        // scheduledExecutorService.scheduleWithFixedDelay(new TicketGenerator(num, System.currentTimeMillis()), 2, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new TicketGenerator(num, System.currentTimeMillis()), 2, 3, TimeUnit.SECONDS);
    }

    class Job implements Runnable {

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                try {
                    DelayTask task = queue.take();
                    System.out.println(task.name + ":" + System.currentTimeMillis());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        DelayTaskTest test = new DelayTaskTest();
        for (int i = 0; i < 2; i++) {
            test.method1();
        }
        // new DelayTaskTest().build();
        // queue.add(new DelayTask("1", 1L, TimeUnit.SECONDS));
        // queue.add(new DelayTask("2", 2L, TimeUnit.SECONDS));
        // queue.add(new DelayTask("3", 3L, TimeUnit.SECONDS));


    }
}
