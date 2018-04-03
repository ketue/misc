package com.monical.jdk.reflect;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author zijie.cao
 * @date 2018-03-07 23:19:31
 */
public class TypeVariableBean<K extends InputStream & Closeable, V> {
    // K 的上边界是 InputStream
    K key;
    // 没有指定的话 ，V 的 上边界 属于 Object
    V value;
    // 不属于 TypeTypeVariable
    V[] values;
    String str;
    List<K> kList;

    public static void main(String[] args) {
        TypeVariableBean bean = new TypeVariableBean<FileInputStream, String>();
        Field fk = null;
        TypeVariable keyType = null;
        try {
            fk = TypeVariableBean.class.getDeclaredField("key");
            keyType = (TypeVariable) fk.getGenericType();
            System.out.println(keyType.getName());
            System.out.println(keyType.getGenericDeclaration());
            for (Type t : keyType.getBounds()) {
                System.out.println(t);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
