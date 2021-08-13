package com.example.client.okclient;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 1、创建OkHttpClient对象
 * 2、创建Request对象
 * 3、将Request封装为Call
 * 4、通过Call发起请求 调用execute是同步请求 调用enqueue方法异步
 */
public class OKHttpClient {


    public static void main(String[] args) {
        testGet("https://www.baidu.com/");
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

    }


}
