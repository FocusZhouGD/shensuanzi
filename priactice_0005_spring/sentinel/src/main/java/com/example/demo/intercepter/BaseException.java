package com.example.demo.intercepter;

public class BaseException extends RuntimeException {
    public BaseException(int i, String s) {
    }


    public BaseException(String s) {
        super(s);
    }

}
