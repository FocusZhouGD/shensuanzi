package com.example.tools.theadpoll;

public class TestsThreadPoolUtil {
    public static void main(String[] args) {
        ThreadPoolUtil.poolSubmit().submit(()->{
            sendNoticeMsg();
        });
    }

    private static void sendNoticeMsg() {
    }
}
