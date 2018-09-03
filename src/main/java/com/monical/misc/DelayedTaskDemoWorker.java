package com.monical.misc;

/**
 * @author zijie.cao
 * @date 2018-08-13 23:48:16
 */
public class DelayedTaskDemoWorker {

    private final DelayedTaskQueue delayedTasks = new DelayedTaskQueue();

    public void execute() {


    }

    public void schedule(DelayedTask task, long at) {
        delayedTasks.add(task, at);
    }

    public void unschedule(DelayedTask task) {
        delayedTasks.remove(task);

    }
}
