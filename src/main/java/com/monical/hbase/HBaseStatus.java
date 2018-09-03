package com.monical.hbase;

/**
 * @author zijie.cao
 * @date 2018-04-21 22:35:04
 */
public enum HBaseStatus {

    idle, active, close;

    // idle --> active
    // active ---> close
    // active ---> idle
}
