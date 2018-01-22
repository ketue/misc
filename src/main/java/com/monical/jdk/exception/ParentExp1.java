package com.monical.jdk.exception;

/**
 * @author zijie.cao
 * @date 2018-01-22 14:44:02
 */
public class ParentExp1 extends RuntimeException {

    public ParentExp1(String msg) {
        super(msg);
    }

    public ParentExp1(String msg, Throwable t) {
        super(msg, t);
    }

}
