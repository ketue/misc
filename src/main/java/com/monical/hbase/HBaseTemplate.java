package com.monical.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zijie.cao
 * @date 2018-04-16 23:39:11
 */
public class HBaseTemplate {


    private static Configuration conf = HBaseConfiguration.create();

    private static ExecutorService pool = Executors.newFixedThreadPool(300);

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "localhost");
    }

    public static Connection getConn() throws IOException, InterruptedException {
        Connection conn = null;
        int i = 0;
        while (conn != null && i++ < 5) {
            conn = ConnectionFactory.createConnection(conf, pool);
            if (conn != null) return conn;
            Thread.sleep(100);
        }
        throw new RuntimeException("no connection available");
    }

    public static void closeConn(Connection conn) {



    }

    public static void createTable(String tableName, String[] families) throws IOException {

        // ======= deprecated =======
        // HBaseAdmin admin = new HBaseAdmin(conf);
        // HTableDescriptor descriptor = new HTableDescriptor(tableName);

        Connection conn = ConnectionFactory.createConnection(conf);
        Admin hBaseAdmin = conn.getAdmin();
        TableName table = TableName.valueOf(tableName);
        HTableDescriptor descriptor = new HTableDescriptor(table);
        for (String family : families) {
            descriptor.addFamily(new HColumnDescriptor(family));
        }
        if (hBaseAdmin.tableExists(table)) {
            System.out.println("table " + tableName + " exists");
            return;
        }

        hBaseAdmin.createTable(descriptor);
    }

    /**
     * 根据列名和列值插入
     *
     * @param rowKey
     * @param tableName
     * @param column1
     * @param value1
     * @param column2
     * @param value2
     * @throws IOException
     */
    public static void addData(String rowKey, String tableName,
                               String[] column1, String[] value1, String[] column2, String[] value2) throws IOException {
        Put put = new Put(Bytes.toBytes(rowKey));
        HTable table = new HTable(conf, Bytes.toBytes(tableName));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        HTableDescriptor table1 = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor[] descriptors = table1.getColumnFamilies();
        for (HColumnDescriptor d : descriptors) {
            String familyName = d.getNameAsString();
            if (familyName.equals("article")) { // article列族put数据
                for (int j = 0; j < column1.length; j++) {
                    // put.add(Bytes.toBytes(familyName),
                    //         Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));

                    put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));

                }
            }
            if (familyName.equals("author")) { // author列族put数据
                for (int j = 0; j < column2.length; j++) {
                    put.add(Bytes.toBytes(familyName),
                            Bytes.toBytes(column2[j]), Bytes.toBytes(value2[j]));
                }
            }

        }
        t.put(put);

        System.out.println("add data success!");
    }

    /**
     * 根据rowkey查询表格
     *
     * @param tableName 表名
     * @param rowKey    pk
     * @return
     * @throws IOException
     */
    public static Result getResult(String tableName, String rowKey)
            throws IOException {

        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = t.get(get);

        // result.list() ### deprecated
        printCell(result);
        return result;
    }

    public static void printCell(Result result) {
        for (Cell cell : result.listCells()) {
            System.out.println("family:" + Bytes.toString(cell.getFamilyArray()));
            System.out.println("qualifier:" + Bytes.toString(cell.getQualifierArray()));
            System.out.println("value:" + cell.getValueArray());
            System.out.println("timestamp:" + cell.getTimestamp());
        }
    }

    /**
     * 全表扫描
     *
     * @param tableName
     * @throws IOException
     */
    public static void getResultScan(String tableName) throws IOException {

        Scan scan = new Scan();
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));

        ResultScanner scanner = t.getScanner(scan);
        for (Result result : scanner) {
            printCell(result);
        }

    }

    /**
     * 区域范围内扫描表
     *
     * @param tableName
     * @param start_rowkey
     * @param stop_rowkey
     * @throws IOException
     */
    public static void getResultScan(String tableName, String start_rowkey,
                                     String stop_rowkey) throws IOException {
        Scan scan = new Scan();
        scan.withStartRow(Bytes.toBytes(start_rowkey));
        scan.withStopRow(Bytes.toBytes(stop_rowkey));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));

        ResultScanner scanner = t.getScanner(scan);
        for (Result result : scanner) {
            printCell(result);
        }
    }

    /**
     * 查询指定family下指定column的cell
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     * @throws IOException
     */
    public static void getResultByColumn(String tableName, String rowKey,
                                         String familyName, String columnName) throws IOException {

        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        printCell(t.get(get));
    }

    public static void updateTable(String tableName, String rowKey,
                                   String familyName, String columnName, String value)
            throws IOException {

        Put put = new Put(Bytes.toBytes(rowKey));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
        t.put(put);
        System.out.println("update table success!");

    }

    /**
     * 查询指定行的多个timestamp版本
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     * @throws IOException
     */
    public static void getResultByVersion(String tableName, String rowKey,
                                          String familyName, String columnName) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)).setMaxVersions(5);
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        printCell(t.get(get));
    }

    /**
     * 删除指定rowkey
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     * @throws IOException
     */
    public static void deleteColumn(String tableName, String rowKey,
                                    String familyName, String columnName) throws IOException {

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        t.delete(delete);
        System.out.println("delete column:" + columnName + "success");

    }

    /**
     * 删除指定rowkey下的所有column
     * 删除行
     *
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void deleteAllColumn(String tableName, String rowKey)
            throws IOException {

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        Connection conn = ConnectionFactory.createConnection(conf);
        Table t = conn.getTable(TableName.valueOf(tableName));
        t.delete(delete);
        System.out.println("delete all column with :" + rowKey + "success");
    }

    /**
     * @param tableName
     * @throws IOException
     */
    public static void deleteTable(String tableName) throws IOException {
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin hBaseAdmin = conn.getAdmin();
        TableName table = TableName.valueOf(tableName);
        hBaseAdmin.disableTable(table);
        hBaseAdmin.deleteTable(table);
        System.out.println("tableName:" + tableName + " deleted.");
    }

    public static void main(String[] args) throws IOException {
        // 创建表
        String tableName = "blog2";
        String[] family = {"article", "author"};
        createTable(tableName, family);

        // 为表添加数据

        String[] column1 = {"title", "content", "tag"};
        String[] value1 = {
                "Head First HBase",
                "HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data.",
                "Hadoop,HBase,NoSQL"};
        String[] column2 = {"name", "nickname"};
        String[] value2 = {"nicholas", "lee"};
        addData("rowkey1", "blog2", column1, value1, column2, value2);
        addData("rowkey2", "blog2", column1, value1, column2, value2);
        addData("rowkey3", "blog2", column1, value1, column2, value2);

        // 遍历查询
        getResultScan("blog2", "rowkey4", "rowkey5");
        // 查询
        getResult("blog2", "rowkey1");

        // 查询某一列的值
        getResultByColumn("blog2", "rowkey1", "author", "name");

        // 更新列
        updateTable("blog2", "rowkey1", "author", "name", "bin");

        // 查询某一列的值
        getResultByColumn("blog2", "rowkey1", "author", "name");

        // 查询某列的多版本
        getResultByVersion("blog2", "rowkey1", "author", "name");

        // 删除一列
        deleteColumn("blog2", "rowkey1", "author", "nickname");

        // 删除所有列
        deleteAllColumn("blog2", "rowkey1");

        // 删除表
        deleteTable("blog2");
    }


}
