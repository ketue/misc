package com.monical.hbase;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zijie.cao
 * @date 2018-04-21 22:32:12
 */
public class HBaseConnectionPool {

    private int initSize;
    private int maxSize;

    private ConcurrentHashMap<String, HBaseConnectionEntity> idleConns = null;
    private ConcurrentHashMap<String, HBaseConnectionEntity> activeConns = null;
    public HBaseConnectionPool(int initSize, int maxSize) {
        this.initSize = initSize;
        this.maxSize = maxSize;

        this.idleConns = new ConcurrentHashMap<>();
        this.activeConns = new ConcurrentHashMap<>();
    }
}
