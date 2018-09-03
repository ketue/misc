package com.monical.misc;

/**
 * @author zijie.cao
 * @date 2018-08-13 23:46:16
 */
public class DelayTaskDemo implements DelayedTask {

    @Override
    public void run(long now) {

        try {
            Thread.sleep(2000);
            System.out.println("long time process");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
