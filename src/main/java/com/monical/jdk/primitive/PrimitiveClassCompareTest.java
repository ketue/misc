package com.monical.jdk.primitive;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zijie.cao
 * @date 2018-02-05 10:24:27
 */
public class PrimitiveClassCompareTest {

    @Test
    public void testPrimitive() {
        Float f1 = 128f;
        Float f2 = 128f;
        Assert.assertFalse("float '==' result : false", f1 == f2);
        Assert.assertTrue("f1 == 128F", f1 == 128.0d);

        Integer i1 = 123;
        Integer i2 = 123;
        System.out.println(i1 == i2);

        String s1 = "s111";
        String s2 = "s111";
        String s3 = new String("s111");
        String s4 = new String(s1);
        String s5 = new String(s2);
        System.out.println(s1 == s2);
        System.out.println(s1 == s3.intern());
        System.out.println(s4 == s5);
        System.out.println(s1 == "s111");
    }
}
