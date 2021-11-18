package com.example.transactional.util;

import java.util.concurrent.*;

public class ThreadPools {

    public static ExecutorService exec = new ThreadPoolExecutor(
            20,
            300,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            },
            new ThreadPoolExecutor.AbortPolicy());
}
