package com.monical.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zijie.cao
 * @date 2018-04-21 22:41:18
 */
public class HBaseUtil {

    private static Configuration conf = HBaseConfiguration.create();
    private static ExecutorService pool = Executors.newFixedThreadPool(300);

    public static Connection getConn() throws IOException, InterruptedException {
        Connection conn = null;
        int i = 0;
        while (conn != null && i++ < 5) {
            conn = ConnectionFactory.createConnection(conf, pool);
            if (conn != null) return conn;
            Thread.sleep(100);
        }
        throw new RuntimeException("no conn available");
    }
}
