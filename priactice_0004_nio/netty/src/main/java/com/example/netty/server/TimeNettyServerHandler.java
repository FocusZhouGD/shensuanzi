package com.example.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * extends  ChannelHandlerAdapter
 * 1、对网络事件进行读写操作，channelRead()和exceptionCaught()
 */
public class TimeNettyServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        System.out.println("catch exception...");
        ctx.close();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        ByteBuf buffer = (ByteBuf) msg;
        //这是spring dataBuffer
        //DataBuffer buffer = (DataBuffer) msg;
        //类型转换
        byte[] req = new byte[buffer.readableBytes()];
        buffer.readBytes(req);
        //构造请求消息
        String body = new String(req,"UTF-8");
        System.out.println("the time server receive order :"+body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //
        ctx.write(resp);



    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        System.out.println("catch channel read complete...");
        ctx.flush();
    }
}
