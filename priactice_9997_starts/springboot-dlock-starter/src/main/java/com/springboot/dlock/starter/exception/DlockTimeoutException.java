package com.springboot.dlock.starter.exception;

/**
 * @ClassName DlockTimeoutException
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/17 14:25
 * @Version 1.0
 **/
public class DlockTimeoutException extends RuntimeException{

    public DlockTimeoutException(){

    }

    public DlockTimeoutException(String message) {
        super(message);
    }

    public DlockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
