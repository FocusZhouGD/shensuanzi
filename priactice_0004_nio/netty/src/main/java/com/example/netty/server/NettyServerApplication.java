package com.example.netty.server;

//@SpringBootApplication
public class NettyServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(NettyServerApplication.class, args);
        HttpServer server =new HttpServer(false,8089);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
