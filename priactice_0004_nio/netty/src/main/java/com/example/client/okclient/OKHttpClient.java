package com.example.client.okclient;

import okhttp3.*;
import okio.BufferedSink;

import java.io.IOException;

/**
 * 1、创建OkHttpClient对象
 * 2、创建Request对象
 * 3、将Request封装为Call
 * 4、通过Call发起请求 调用execute是同步请求 调用enqueue方法异步
 */
public class OKHttpClient {


    public static void main(String[] args) {
        //testGet("http://localhost:8089/test");
        testPost("http://localhost:8089/test");
    }

    public static void testGet(String url){
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("ok client response:"+ response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testPost(String url){
        OkHttpClient client =new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),"");
        Request request =new Request.Builder().url(url).post(requestBody).build();
        Call call = client.newCall(request);
//        Response response = call.execute();
//        System.out.println("ok client response:" + response.body().string());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("调用失败了。。。。。"+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("ok http client response :"+ response.body().string());
            }
        });

    }


}
