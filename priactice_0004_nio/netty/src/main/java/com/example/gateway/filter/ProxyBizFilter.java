package com.example.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class ProxyBizFilter implements HttpRequestFilter {

    public static HttpRequestFilter newInstance() {
        return new ProxyBizFilter();
    }

    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {

    }
}
