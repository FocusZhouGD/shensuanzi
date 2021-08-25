package com.example.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * http server
 * 1、port proxyServer
 */
public class HttpInboundServer {

    private int port;

    private String proxyServer;


    public HttpInboundServer(int port, String proxyServer) {
        this.port = port;
        this.proxyServer = proxyServer;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(16);

        ServerBootstrap b = new ServerBootstrap();
        //TCP/IP 协议 用来初始化服务端可连接队列
        b.option(ChannelOption.SO_BACKLOG, 128)
                //TCP/IP 协议针对TCP默认开启了Nagle算法，nagle算法通过减少需要的传输的数据包，来优化网络
                .option(ChannelOption.TCP_NODELAY, true)
                //端口复用，一个端口释放后会等待两分钟之后才能再被使用
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                //支持多个线程或者进程绑定到同一端口，提高服务器程序的性能，多个套接字绑定同一个TCP/UDP端口
                .option(EpollChannelOption.SO_REUSEPORT, true)
                // TCP心跳机制，默认的心跳间隔是7200s即2小时，netty默认关闭该功能
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

                b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new HttpInboundInitializer(this.proxyServer));


        try {
            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + "/");
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
