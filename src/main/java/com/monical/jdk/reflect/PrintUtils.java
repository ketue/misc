package com.monical.jdk.reflect;

import java.lang.reflect.Type;

/**
 * @author zijie.cao
 * @date 2018-03-08 13:06:34
 */
public class PrintUtils {

    static void process(Person p) {

    }


    public static void print(Object type) {
        System.out.println(type);
    }

    public static void print(Type type) {
        System.out.println(type + "\t" + "type");
    }


}
