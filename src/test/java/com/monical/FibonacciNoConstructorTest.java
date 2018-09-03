package com.monical;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author zijie.cao
 * @date 2018-08-12 14:39:32
 */

@RunWith(Parameterized.class)
public class FibonacciNoConstructorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
        });
    }

    @Parameterized.Parameter
    public int input;
    @Parameterized.Parameter(1)
    public int expected;

    @Test
    public void test() {
        Assert.assertEquals(expected, Fibonacci.compute(input));
    }


}

