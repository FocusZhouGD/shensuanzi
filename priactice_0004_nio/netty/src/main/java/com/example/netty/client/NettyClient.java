package com.example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class NettyClient {

    public static void main(String[] args) {

        nettyclientdemo();
    }

    private static void nettyclientdemo() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                //绑定客户端通道
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new HttpClientCodec());
                        channel.pipeline().addLast(new HttpObjectAggregator(256));
                        channel.pipeline().addLast(new HttpContentDecompressor());
                        channel.pipeline().addLast(new HttpClientHandler());
               //https://blog.csdn.net/feinifi/article/details/102981475
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8089).sync();
            //监听服务器关闭监听
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }


}
