package com.monical.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zijie.cao
 * @date 2018-07-16 11:57:48
 */
public class Uncategoried {

    Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        new Uncategoried().findHighestBitPosition(16l);
    }

    public void findHighestBitPosition(Long num) {
        String s = Long.toBinaryString(num);
        log.debug(s);
        int mask = 01;
        int p = 0;
        int len = 16;
        for (int i = 0; i < len; i++, num >>= 1) {
            if ((num & mask) == mask) {
                p = i + 1;
            }
        }
        log.debug("{}", p);
    }

    // https://blog.csdn.net/JimmyCai0101/article/details/25507945
    // 求二进制数中高位或低位第一次出现1所在的位置
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
