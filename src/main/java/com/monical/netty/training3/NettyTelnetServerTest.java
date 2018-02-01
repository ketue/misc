package com.monical.netty.training3;

import org.junit.Test;

/**
 * @author zijie.cao
 * @date 2018-02-01 14:24:40
 */
public class NettyTelnetServerTest {

    @Test
    public void test() {
        NettyTelnetServer nettyTelnetServer = new NettyTelnetServer();
        try {
            nettyTelnetServer.start();
        } catch (InterruptedException e) {
            nettyTelnetServer.close();
        }
    }

}
