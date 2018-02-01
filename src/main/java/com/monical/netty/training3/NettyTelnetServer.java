package com.monical.netty.training3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author zijie.cao
 * @date 2018-02-01 14:12:22
 */
public class NettyTelnetServer {

    private static int port = 10002;
    private ServerBootstrap serverBootstrap = null;
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() throws InterruptedException {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap = serverBootstrap.group(bossGroup, workerGroup).handler(new LoggingHandler()).channel(NioServerSocketChannel.class).childHandler(new NettyTelnetInputHandler());
        // 绑定对应的端口号,并启动开始监听端口上的连接
        Channel ch = serverBootstrap.bind(port).sync().channel();

        // 等待关闭,同步端口
        ch.closeFuture().sync();
    }

    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
