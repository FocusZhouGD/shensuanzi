package com.example.gateway.outbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http2.HttpUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler {

    private String backendUrl;

    public HttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/")?backendUrl.substring(0,backendUrl.length()-1):backendUrl;
    }

    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String url = this.backendUrl + fullRequest.uri();
        FullHttpResponse response=null;
        HttpHeaders headers = fullRequest.headers();
        // 发起请求使用httpclient 或者okclient客户端
        String body= invokeMethodOkClient(url);
        try {
            byte[] bytesArray = body.getBytes("UTF-8");
            response=new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(bytesArray));
            response.headers().set("Content-Type", "application/json");
            response.headers().set("X-proxy-tag", this.getClass().getSimpleName());
            response.headers().setInt("Content-Length", bytesArray.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            response=new DefaultFullHttpResponse(HTTP_1_1,NO_CONTENT);
            exceptionCaught(ctx, e);
        }finally {
//            if (fullRequest != null) {
//                if(null == response){
//                    response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
//                }
//                if (!HttpUtil.isKeepAlive(fullRequest)) {
//                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//                } else {
//                    //response.headers().set(CONNECTION, KEEP_ALIVE);
//                    ctx.write(response);
//                }
//            }
            ctx.flush();

        }


    }

    /**
     * 发起客户端调用
     * @param url
     * @return
     */
    private String invokeMethodOkClient( String url) {
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("ok client response:"+ response.body().string());
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void exceptionCaught(ChannelHandlerContext ctx, UnsupportedEncodingException e) {
        e.printStackTrace();
        ctx.close();
    }
}
