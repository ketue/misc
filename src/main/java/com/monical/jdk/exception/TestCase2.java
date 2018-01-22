package com.monical.jdk.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author zijie.cao
 * @date 2018-01-22 14:14:00
 */
public class TestCase2 {

    void testSuperExceptionClass() {

        try {
            method2();
        } catch (Exception e) {
            System.out.println("error occurs." + e.getMessage());
            e.printStackTrace();
        }

    }

    void testSameExceptionClass() {

        try {
            method2();
        } catch (ParentExp1 e) {
            System.out.println("error occurs." + e.getMessage());
            e.printStackTrace();
        }

    }

    void testExtendsExceptionClass() {

        try {
            method2();
        } catch (ChildExp1 e) {
            if (e instanceof ParentExp1) {
                System.out.println("error occurs." + e.getMessage());
            }
            e.printStackTrace();
        }

    }

    void method2() {
        throw new ParentExp1("method2 throws!");
    }

    public static void main(String[] args) {
        TestCase2 testCase = new TestCase2();
        // testCase.testSameExceptionClass();
        // testCase.testSuperExceptionClass();
        testCase.testExtendsExceptionClass();
    }

    // 运行时异常尽量缩窄范围，不要用runtimeException/Exception/throwable来接收异常
    // 父类异常能用其上层类接收


}
