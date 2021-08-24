package com.example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeNettyClient {
    public void connect(int port,String host){
        //配置客户端NIO线程组
        EventLoopGroup group =new NioEventLoopGroup();
        try {
            Bootstrap b =new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeNettyClientHandler());
                        }
                    });
            try {
                //发起异步连接
                ChannelFuture f = b.connect(host,port).sync();
                //等待客服端链路关闭
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            group.shutdownGracefully();

        }

    }
}
