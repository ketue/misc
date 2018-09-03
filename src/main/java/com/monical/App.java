package com.monical;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.monical.esapi.XSSFilter;
import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.util.*;

/**
 * Hello world!
 */

public class App {

    private Logger logger = LoggerFactory.getLogger(App.class);

    @Test
    public void testCircle() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("testCircle.xml");
        System.out.println(bf.getBean("testA"));
    }

    @Test
    public void testCond() {

        if (1 == 1) {
            System.out.println("1 == 1");
        } else if (2 == 2) {
            System.out.println("2 == 2");
        }
    }

    @Test
    public void for1() {

        List list = new ArrayList();


        for (Object o : list) {
            System.out.println("123" + o);
        }
    }


    @Test
    public void testListIterator() {

        List<ProjectTicket> localProjectTicketList = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            localProjectTicketList.add(new ProjectTicket(i));
        }

        final int invokerNo = 5;
        final Map<Integer, List<ProjectTicket>> invokerParamsMap = Maps.newHashMap();
        for (int i = 0; i < invokerNo; i++) {
            List<ProjectTicket> invokerParams = Lists.newArrayList();
            invokerParamsMap.put(i, invokerParams);
        }
        ListIterator<ProjectTicket> listIterator = localProjectTicketList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("while" + listIterator.nextIndex());
            for (int i = 0; i < invokerNo; i++) {
                if (listIterator.hasNext()) {
                    System.out.println("while + if " + listIterator.nextIndex());
                    invokerParamsMap.get(i).add(listIterator.next());
                }
            }
        }

        for (Map.Entry<Integer, List<ProjectTicket>> entry : invokerParamsMap.entrySet()) {
            System.out.println("---- start ----");
            System.out.print(entry.getKey());
            System.out.println();

            for (ProjectTicket ticket : entry.getValue()) {
                System.out.println(ticket.getProjectTicketId());
            }
            System.out.println("---- end ----");
        }
        // System.out.println(invokerParamsMap);
    }

    @Test
    public void testHappensBefore() throws InterruptedException {
        ProjectTicket1 demo = new ProjectTicket1();
        // for (int i = 0; i < 10000; i++) {
        //     new Thread(new ProjectTicketTask(demo)).start();
        //     // new Thread(new ProjectTicketReadTask(demo)).start();
        // }
        // new Thread(new ProjectTicketReadTask(demo)).start();
        Thread w = new Thread(new ProjectTicketTask(demo));
        Thread r = new Thread(new ProjectTicketReadTask(demo));
        w.start();
        r.start();
        w.join();
        r.join();
    }

    private final static int TOTAL_THREADS = 2;

    @Test
    public void testVolatile() throws InterruptedException {
        VolatileData volatileData = new VolatileData();

        Thread[] threads = new Thread[TOTAL_THREADS];
        for (int i = 0; i < TOTAL_THREADS; ++i)
            threads[i] = new VolatileThread(volatileData);

        // Start all reader threads.
        for (int i = 0; i < TOTAL_THREADS; ++i)
            threads[i].start();

        // Wait for all threads to terminate.
        for (int i = 0; i < TOTAL_THREADS; ++i)
            threads[i].join();
    }

    @Test
    public void stripxss() {

        String s = "té我\uD83D叶\uDC47永青以 极具个人风格的诗情\uDBFF\uDC00绘黄桷坪发电厂等现代工业图腾和原始神秘的热带动植物";
        String cleaned = ESAPI.encoder().encodeForHTML(s);
        cleaned = ESAPI.encoder().decodeForHTML(cleaned);
        System.out.println(cleaned);
    }

    @Test
    public void stripHtml() {
        String s = "平仮名／ひらがな／ヒラガナ）<script>《》</script>叶永青以 极具个人风格的诗情\uDBFF\uDC00绘黄桷坪发电厂等现代工业图腾和原始神秘的热带动植物";

        s = "\uD83D\uDC47";
        s = XSSFilter.filterSpecStr(s);
        System.out.println(s);

        System.out.println("👽💔\uD83D\uDC47".length() == 4);
    }

    @Test
    public void testException() {
        Integer i1 = null;

        try {
            int i = i1;
        } catch (Exception e) {
            logger.error("error occurs", e);
        }
    }

    @Test
    public void testIterator() {
        Set<Integer> set = ImmutableSet.of(1, 2, 3, 4);
        for (Iterator<Integer> ite = set.iterator();
             ite.hasNext(); ) {
            System.out.println(ite.next());
            System.out.println(ite.next());
            System.out.println(ite.next());
        }
    }
}

class ProjectTicket {
    private int projectTicketId;

    public ProjectTicket() {
    }

    public ProjectTicket(int projectTicketId) {
        this.projectTicketId = projectTicketId;
    }

    public int getProjectTicketId() {
        return projectTicketId;
    }

    public void setProjectTicketId(int projectTicketId) {
        this.projectTicketId = projectTicketId;
    }
}

class ProjectTicketTask implements Runnable {
    ProjectTicket1 demo;

    public ProjectTicketTask(ProjectTicket1 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.writea(2);
    }
}

class ProjectTicketReadTask implements Runnable {
    ProjectTicket1 demo;

    public ProjectTicketReadTask(ProjectTicket1 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        System.out.println(demo.reada());
    }
}

class ProjectTicket1 {

    private int i = 0;

    public void writea(int j) {
        i = j;
    }

    public int reada() {
        return i;
    }

    private int projectTicketId = 20;
    private BigInteger bi;

    public ProjectTicket1() {

    }

    public void writable() {
        projectTicketId = 200;
    }

    public void write() {

        for (int i = 0; i < 100; i++) {
            // System.out.print(2);
        }
        bi = new BigInteger("234");
        // System.out.println();
        // projectTicketId = 3;
        // if (projectTicketId != 3)
        // if (projectTicketId >2) {
        System.out.println(Thread.currentThread().getId() + "-" + projectTicketId);
        System.out.println(Thread.currentThread().getId() + "--" + projectTicketId);
        projectTicketId = 2;
        // }
        // System.out.println("read -- " + projectTicketId);
    }

    public void read() {
        // if (projectTicketId != 3)
        System.out.println("<<" + projectTicketId);

    }


    public ProjectTicket1(int projectTicketId) {
        this.projectTicketId = projectTicketId;
    }

    public int getProjectTicketId() {
        return projectTicketId;
    }

    public void setProjectTicketId(int projectTicketId) {
        this.projectTicketId = projectTicketId;
    }
}

class VolatileData {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        ++counter;
    }
}

class VolatileThread extends Thread {
    private final VolatileData data;

    public VolatileThread(VolatileData data) {
        this.data = data;
    }

    @Override
    public void run() {
        int oldValue = data.getCounter();
        System.out.println("[Thread " + Thread.currentThread().getId()
                + "]: Old value = " + oldValue);

        data.increaseCounter();

        int newValue = data.getCounter();
        System.out.println("[Thread " + Thread.currentThread().getId()
                + "]: New value = " + newValue);
    }
}