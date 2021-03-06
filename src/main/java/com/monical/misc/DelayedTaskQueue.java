package com.monical.misc;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author zijie.cao
 * @date 2018-08-13 23:45:12
 */
public class DelayedTaskQueue {

    private PriorityQueue<Entry> tasks;

    public DelayedTaskQueue() {
        tasks = new PriorityQueue<Entry>();
    }

    /**
     * Schedule a task for execution in the future.
     *
     * @param task the task to execute
     * @param at   the time at which to
     */
    public void add(DelayedTask task, long at) {
        tasks.add(new Entry(task, at));
    }

    /**
     * Remove a task from the queue if it is present
     *
     * @param task the task to be removed
     * @returns true if a task was removed as a result of this call
     */
    public boolean remove(DelayedTask task) {
        boolean wasRemoved = false;
        Iterator<Entry> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.task.equals(task)) {
                iterator.remove();
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    /**
     * Get amount of time in milliseconds until the next event. Returns Long.MAX_VALUE if no tasks are scheduled.
     *
     * @return the remaining time in milliseconds
     */
    public long nextTimeout(long now) {
        if (tasks.isEmpty())
            return Long.MAX_VALUE;
        else
            return Math.max(tasks.peek().timeout - now, 0);
    }

    /**
     * Run any ready tasks.
     *
     * @param now the current time
     */
    public void poll(long now) {
        while (!tasks.isEmpty() && tasks.peek().timeout <= now) {
            Entry entry = tasks.poll();
            entry.task.run(now);
        }
    }

    private static class Entry implements Comparable<Entry> {
        DelayedTask task;
        long timeout;

        public Entry(DelayedTask task, long timeout) {
            this.task = task;
            this.timeout = timeout;
        }

        @Override
        public int compareTo(Entry entry) {
            return Long.compare(timeout, entry.timeout);
        }
    }
}
