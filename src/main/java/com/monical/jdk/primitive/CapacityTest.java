package com.monical.jdk.primitive;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author zijie.cao
 * @date 2018-01-26 11:20:16
 */
public class CapacityTest {

    public static final Log log = LogFactory.getLog("CapacityTest");

    @Test
    public void test1() throws UnsupportedEncodingException {
        log.info(Charset.defaultCharset());
        // string
        String s = new String("1Netty in Action rocks!我");

        log.info(s.getBytes().length);
        log.info(s.getBytes("utf-8").length);
        for (Byte b : s.getBytes()) {
            System.out.print(b + "\t");
        }
        System.out.println();
        for (Byte b : s.getBytes("gb2312")) {
            System.out.print(b + "\t");
        }
        System.out.println();
        // char
        char c = '我';
        // s.toCharArray() ==> char[]
        char[] ca = new char[]{'1', 'N', 'e', 't', 't', 'y', ' ', 'i', 'n', ' ', 'A', 'c', 't', 'i', 'o', 'n'};
        log.info(ca);
        // char[] ==> string
        String string = new String(ca, 1, 15);
        log.info(string);
        // byte[] ==> string
        byte[] bs = {49, 78, 101, 116, 116, 121, 32};
        string = new String(bs, 1, 5);
        // string ==> byte[]
        byte[] bytes = string.getBytes();
        log.info(string);
        /****  char[] byte[]  ***/
        byte[] chinese = {-26, -120, -111};

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("我Netty in Action rocks!", utf8);    //1

        System.out.println((char)buf.getByte(0));                    //2
        // System.out.println((char[]) chinese);                    //2
        System.out.println(buf.getByte(0));
    }
}
