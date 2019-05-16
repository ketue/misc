package com.monical;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zijie.cao
 * @date 2018-06-15 17:22:09
 */
public class Test2 {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test1() throws InterruptedException {
        System.out.println(new Date().before(new Date()));
        Date d1 = new Date();
        for(int i=0;i<100000;i++){}
        // TimeUnit.SECONDS.sleep(1);
        Date d2 = new Date();
        System.out.println(d1.before(d2));

        boolean d = d1 == null ? true : d1.before(new Date());

    }

    @Test
    public void testAddShutDownHook() {

        Signal signal = new Signal("USR2");
        Signal.handle(signal, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                // close 订阅者
                // close thread
                // exit

            }
        });
    }

    @Test
    public void testStringSplit() {
        String str = "a,,b,c,,";
        // 需做最后一个分隔符后有无内容的检查
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);
        List<String> list = Splitter.on(",").omitEmptyStrings().splitToList(str);
        System.out.println(list.size());
    }

    @Test
    public void testSplit() {
        System.out.println("".substring(0,10));
    }

    @Test
    public void testBigDecimalCompare() {
        BigDecimal bg = new BigDecimal("20.00");
        BigDecimal bg2 = new BigDecimal("20.00");
        System.out.println(bg.equals(bg2));

        List<String> list = new ArrayList<>();
        System.out.println(list);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void test() {
        HashMap map = new HashMap();
        Object o1 = map.put("123", null);
        // Object o2 = map.put(null, null);
        Object o3 = map.put(null, "222");
        Object o4 = map.put(null, "2232");

        System.out.println(o1 + "\t " + o4 + "\t" + o3 + "\t" + map.get(null));
        Object o = -1 << 4;
        System.out.println(1 << 30);

        map.put(123, 123);
        map.put(1234, 1234);
        System.out.println(map.get(1234));
        System.out.println(this.tableSizeFor(32));
    }

    @Test
    public void test2() {
        String str = "a";
        long time = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            str += "c";
        }
        System.out.println("加号所花费的时间：");
        System.out.println(System.currentTimeMillis() - time);
        String str2 = "a";
        time = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            str2.concat("c");
        }
        System.out.println("cancat方法所花费的时间：");
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder("a");
        for (int i = 0; i < 50000; i++) {
            stringBuilder.append("c");
        }
        String str3 = stringBuilder.toString();
        System.out.println("StringBuilder的append方法：");
        System.out.println(System.currentTimeMillis() - time);


        time = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            str += "";
        }
        System.out.println("加号所花费的时间：");
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        String si = null;
        for (int i = 0; i < 50000; i++) {
            si = String.valueOf(i);
        }
        System.out.println("加号所花费的时间：");
        System.out.println(System.currentTimeMillis() - time);

    }

    @Test
    public void testGuavaFIlter() {
        List<AppTest> list = Lists.newArrayList();


        FluentIterable.from(list).anyMatch(new Predicate<AppTest>() {
            @Override
            public boolean apply(AppTest input) {
                return false;
            }
        });
        list.stream().anyMatch(e -> e.getName() == null);
    }


    @Test
    public void testOptional() {


        String o = "123";  // o=null;

        String o1 = "123";
        // o1 = null;
        // BooleanUtils.xor(new Boolean[]{o == null , );

        // if(o == null) return 1;
        // equals() ? 0 , -1
        int i = Boolean.compare(o == null || o1 == null, o != null && o.equals(o1));
        System.out.println(i);
    }

    @Test
    public void testComparisionChain() {

        List<Item> list = Lists.newArrayList();

        list.add(new Item(1, null));
        list.add(new Item(2, "123"));
        list.add(new Item(3, null));
        list.add(new Item(4, "23null"));
        list.add(new Item(5, "123"));

        Ordering<Item> order = new Ordering<Item>() {
            @Override
            public int compare(Item left, Item right) {
                return ComparisonChain.start().compare(left.desc, right.desc, Ordering.natural().nullsFirst()).result();
            }
        };
        list = order.immutableSortedCopy(list);

        printList(list);
    }

    @Test
    public void p() {
        List list = new ArrayList();
        list.add(1);

        list = list.subList(0, 0);

        System.out.println(list);
    }

    void printList(List<Item> items) {

        for (Item item : items) {
            System.out.println(item.toString());
        }
    }


    class Item {
        int id;
        String desc;

        public Item() {

        }

        public Item(int i, String desc) {
            this.id = i;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return this.id + ":" + this.desc;
        }
    }

}

