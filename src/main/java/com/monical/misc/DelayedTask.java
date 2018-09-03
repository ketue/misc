package com.monical.misc;

/**
 * @author zijie.cao
 * @date 2018-08-13 23:44:49
 */
public interface DelayedTask {


    /**
     * Execute the task.
     *
     * @param now current time in milliseconds
     */
    void run(long now);
}
