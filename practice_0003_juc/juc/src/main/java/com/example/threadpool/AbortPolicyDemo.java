package com.example.threadpool;

import java.util.concurrent.*;

/**
 * 线程池的创建严格按照阿里巴巴开发手册
 * 使用ThreadPoolExecutor
 */
public class AbortPolicyDemo {

    public static void main(String[] args) {

//        BlockingQueue<Runnable> workQueue =new ArrayBlockingQueue(100);
//        ThreadFactory factory=r->new Thread(r,"test-thread-pool-");
//
//        ThreadPoolExecutor executor= new ThreadPoolExecutor(5,5, 0L,TimeUnit.SECONDS,workQueue,factory);
//
//        while (true){
//            executor.submit(()->{
//                try {
//                    System.out.println("队列大小："+workQueue.size());
//                    Thread.sleep(10000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }

        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10);
        //RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, handler);
        for(int i=0; i<100; i++) {
           // executor.execute(new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running")));
            try {
                executor.execute(new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running")));
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
