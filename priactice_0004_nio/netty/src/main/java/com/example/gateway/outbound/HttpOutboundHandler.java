package com.example.gateway.outbound;

public class HttpOutboundHandler {

    private String backendUrl;

    public HttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl;
    }
}
