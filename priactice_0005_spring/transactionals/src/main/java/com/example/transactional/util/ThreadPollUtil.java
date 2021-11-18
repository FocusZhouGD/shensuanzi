package com.example.transactional.util;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 次工具类有问题 不可用
 */
public class ThreadPollUtil {

    public static ThreadPoolExecutor threadPool;

    /**
     * 获取线程池
     *
     * @return
     */
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            return threadPool;
        } else {
            synchronized (ThreadPollUtil.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(32));
                }

            }
            return threadPool;
        }
    }

    /**
     * 无返回值执行
     */
    public static void execute(Runnable runnable) {
        getThreadPool().submit(runnable);
    }

    /**
     * 有返回执行
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return  getThreadPool().submit(callable);
    }

    /**
     * 关闭
     */

    public static void shutdown(){
        threadPool.shutdown();
    }
}
