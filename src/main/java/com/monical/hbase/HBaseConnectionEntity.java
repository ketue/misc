package com.monical.hbase;

import org.apache.hadoop.hbase.client.Connection;

/**
 * @author zijie.cao
 * @date 2018-04-21 22:34:08
 */

public class HBaseConnectionEntity {

    private String id;

    private Connection conn;

    private HBaseStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public HBaseStatus getStatus() {
        return status;
    }

    public void setStatus(HBaseStatus status) {
        this.status = status;
    }
}
