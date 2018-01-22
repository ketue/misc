package com.monical.jdk.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author zijie.cao
 * @date 2018-01-22 14:14:00
 */
public class TestCase1 {

    void method1() {
        method2();
    }

    void method2() {

        File file = new File("123");
        try {
            FileInputStream fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // throw e;
        }
        throw new RuntimeException("method2 throws!");
    }

    public static void main(String[] args) {
        TestCase1 testCase = new TestCase1();
        testCase.method1();
    }

    // RuntimeException 必须try，如果throw，上级方法必定要capture


}
