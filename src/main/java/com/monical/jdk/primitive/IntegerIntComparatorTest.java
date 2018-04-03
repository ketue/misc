package com.monical.jdk.primitive;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zijie.cao
 * @date 2018-04-03 22:51:39
 */
public class IntegerIntComparatorTest {

    @Test
    public void test() {
        Integer i = 127;
        Integer j = 127;
        Assert.assertTrue(i == j);
        Assert.assertTrue(i != new Integer(j));
        int j1 = 127;
        Assert.assertTrue(j1 == i);
        Assert.assertTrue(j1 + 10 == i + 10 && i + 10 == new Integer(j) + 10);

        Integer i2 = 128;
        Integer j2 = 128;
        Assert.assertFalse(i2 == j2);

        Integer param1 = 100;
        String param2 = "param2";
        int param3 = 1000;
        int[] param4 = {1, 2, 3, 4};
        method1(param1, param2, param3, param4);
        Assert.assertTrue(param1 == 100);
        Assert.assertTrue(param2 == "param2");
        Assert.assertTrue(param3 == 1000);
        Assert.assertTrue(param4[2] == 100);
    }

    public void method1(Integer i, String j, int k, int[] l) {
        i += 1;
        j = j + "j";
        k = +1;
        l[2] = 100;
    }
}
