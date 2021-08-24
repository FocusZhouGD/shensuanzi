package com.example.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeNettyClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf firstMessage;

    public TimeNettyClientHandler() {
        byte[] req="QUERY TIME ORDER".getBytes();
        firstMessage= Unpooled.buffer(req.length);
        firstMessage.writeByte(req.length);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg;
        byte[] req =new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body =new String(req,"UTF-8");
        System.out.println("now is :"+body);
    }
}
