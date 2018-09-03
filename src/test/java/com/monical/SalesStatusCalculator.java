package com.monical;

import org.junit.Test;

/**
 * @author zijie.cao
 * @date 2018-06-28 22:47:51
 */
public class SalesStatusCalculator {

    @Test
    public void test1() {

/*
        预售10000
                在售01000
        即将开售00100
                售罄00010
        结束00001*/

        int array[] = {1, 2, 4, 8, 16};

        Byte b = 1|2|4|4|8;
        System.out.println(clz2(b));
    }

    /**
     * O(n)的解法
     * @param a
     * @return
     */
    public int clz1(Byte a){
        int n = 0;
        while((a & 0x80) == 0){
            a = (byte) (a << 1);
            n++;
        }
        return n;
    }

    /**
     * 用二分法
     * @param a
     * @return
     */
    public int clz2(Byte a){
        int n = 0;
        if((0xF0 & a) == 0){
            n += 4;
            a = (byte) (a << 4);
        }
        if((0xC0 & a) == 0){
            n += 2;
            a = (byte) (a << 2);
        }
        if((0x80 & a) == 0){
            n += 1;
        }
        return n;
    }

}
