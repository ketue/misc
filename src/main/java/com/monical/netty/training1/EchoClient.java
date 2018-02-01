package com.monical.netty.training1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zijie.cao
 * @date 2018-01-24 15:41:58
 */
public class EchoClient {
    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        args = new String[]{"127.0.0.1", "10001"};
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + "<host><port>");
            return;
        }
        String host = args[0];
        int port = Integer.valueOf(args[1]);
        new EchoClient(host, port).start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(); // 1
        try {
            Bootstrap bootstrap = new Bootstrap();// 2
            bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(host, port)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect().sync();
            System.out.println(f.channel().config().getOption(ChannelOption.SO_BACKLOG));
            f.channel().closeFuture().sync();
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("operation future ???" + future.isSuccess());
                }
            });
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
