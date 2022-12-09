package com.example.tools.callable;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<BaseRspDto<Object>> {

   public CallableTest(){

   }


    @Override
    public BaseRspDto<Object> call() throws Exception {
        System.out.println("call start...");
        return null;
    }
}
