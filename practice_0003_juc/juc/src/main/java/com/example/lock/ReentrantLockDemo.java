package com.example.lock;

public class ReentrantLockDemo {


    public static void main(String[] args) {
        Count count = new Count();
        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    count.get();
                }
            }.start();
        }
        for (int i=0;i<2;i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    count.put();
                }
            }.start();
        }

    }

}
