package com.monical;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author zijie.cao
 * @date 2018-08-12 14:33:19
 */
@RunWith(Parameterized.class)
public class FibonacciTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }
        });
    }
    private int fInput;

    private int fExpected;

    public FibonacciTest(int input, int expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Parameterized.Parameter
    private int input;

    @Parameterized.Parameter(1)
    private int expected;

    @Test
    public void test() {
        assertEquals(fExpected, Fibonacci.compute(fInput));
    }

    @Test
    public void test2() {
        assertEquals(expected, Fibonacci.compute(input));
    }
}
