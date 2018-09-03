package com.monical;

import org.apache.xerces.dom.PSVIAttrNSImpl;

/**
 * @author zijie.cao
 * @date 2018-08-12 14:30:27
 */
public class Fibonacci {

    public static int compute(int n) {

        if (n <= 1) {
            return n;
        }
        return compute(n - 1) + compute(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(compute(3));
    }
}
