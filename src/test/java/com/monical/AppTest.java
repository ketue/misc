package com.monical;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testQueue() {

        Assert.assertEquals(0x010, 0x10);
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("poll=" + queue.poll()); //返回第一个元素，并在队列中删除
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element=" + queue.element()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek=" + queue.peek()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
    }

    public static void main(String[] args) {

        List<Bean> beanList = new ArrayList<>();
        Bean b1 = new Bean();
        b1.setName("111");
        beanList.add(b1);

        b1 = new Bean();
        b1.setName("222");
        beanList.add(b1);

        System.out.println(beanList);
        // 精度计算 不用double
        BigDecimal b = new BigDecimal("12.12");
        System.out.println(b.doubleValue());
        System.out.println(b.floatValue());
        double d1 = 0.01;
        double d2 = 0.02;
        double d3 = (d2 * 3);
        float f = 0.03f;
        System.out.println(d3);
        System.out.println((double) (d1 + d3));
        System.out.println(d1 + f);

    }

    public void testGenerateRandom() {
        long p = 997207; int q= 997219;
        long n = p * q;

        int a = 2, d = 103;
        long e = 9293754887l;
        // int x = (a ^ d) % n;


        for (int i = 2; i < 11; i++) {
            i = Integer.valueOf(RandomStringUtils.randomNumeric(8));
            long x = (long) (Math.pow(i, d) % n);
            long y = (long) Math.pow(i, e) % i;
            System.out.println(i + ":" + x + "," + y);
        }
    }


    @org.junit.Test
    public void testDuplicated() {


        for(int i =0;i < 10;i ++) {
            printnum(i);

        }
    }

    private void printnum(int j) {



            if (j == 8) {
                return;
            }
            System.out.println(j);
    }

    public void testClassLoader() {
        AppTest.class.getClassLoader();
    }


}

class Bean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
