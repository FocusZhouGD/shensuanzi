package com.example.gateway.inbound;

public class TestGateway {
    public static void main(String[] args) {
        HttpInboundServer server =new HttpInboundServer(8091,"127.0.0.1");
        server.run();
    }
}
