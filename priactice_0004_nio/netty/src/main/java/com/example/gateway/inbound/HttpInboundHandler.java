package com.example.gateway.inbound;

import com.example.gateway.filter.HttpRequestFilter;
import com.example.gateway.filter.ProxyBizFilter;
import com.example.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final String proxyServer;

    private HttpOutboundHandler handler;

    private HttpRequestFilter filter;

//    public HttpInboundHandler(String proxyServer, HttpOutboundHandler handler, HttpRequestFilter filter) {
//        this.proxyServer = proxyServer;
//        this.handler = handler;
//        this.filter = filter;
//    }  todo 这样写会有什么问题？ 的确有问题

public HttpInboundHandler(String proxyServer) {
    this.proxyServer = proxyServer;
    handler = new HttpOutboundHandler(this.proxyServer);
    filter = ProxyBizFilter.newInstance();
}


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.fireChannelReadComplete();
        System.out.println("==== channelReadComplete(ChannelHandlerContext ctx)");
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx.fireChannelRead(msg);
        System.out.println("=== channelRead(ChannelHandlerContext ctx)");
        if (false==(msg instanceof FullHttpRequest)){
            return;
        }
        FullHttpRequest fullRequest= (FullHttpRequest) msg;
        filter.filter(fullRequest,ctx);
        handler.handle(fullRequest,ctx);
    }

}
