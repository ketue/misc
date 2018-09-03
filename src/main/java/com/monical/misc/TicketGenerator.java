package com.monical.misc;

/**
 * @author zijie.cao
 * @date 2018-08-14 14:09:16
 */
public class TicketGenerator implements Runnable {

    private int pid;
    private long time;

    public TicketGenerator(int pid, long time) {
        this.pid = pid;
        this.time = time;
    }

    @Override
    public void run() {
        // simulate long time operation
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + "\t" + this.pid + "\t" + this.time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
