package com.example.transactional.oneuntils;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public class ThreadPoolUtil {

    private static ThreadPoolExecutor poolSubmit = null;
    private static ExecutorService executorService = null;
    private static final Object LOCK = new Object();


    /**
     * <p>获得公用线程池</p>
     * <p>使用newCachedThreadPool是因为它可以无限扩大,需求降低时自动缩小,默认60秒回收部分空闲的线程。</p>
     * <p>线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。(大小这条有待验证和测试)</p>
     * <p>它采用SynchronousQueue来避免任务排队,线程之间进行移交的机制,存活的线程可以复用。</p>
     * <p>之所以没有用newFixedThreadPool是因为超过设定数量的任务会进入到队列,队列分无界和有界队列,</p>
     * <p>无界队列可能会造成内存溢出,有界队列需要有多饱和处理机制。</p>
     *
     * @return 线程管理
     */
    public static ExecutorService poll() {
        if (executorService == null || executorService.isShutdown()) {
            synchronized (LOCK) {
                if (executorService == null || executorService.isShutdown()) {
                    executorService = Executors.newCachedThreadPool();
                }
            }
        }
        return executorService;
    }

    /**
     * 基于线程安全的线程池
     *
     * @return
     */
    public static ThreadPoolExecutor poolSubmit() {
        ThreadFactory springThreadFactory = new CustomizableThreadFactory("springThread-pool-");
        if (poolSubmit == null || poolSubmit.isShutdown()) {
            synchronized (LOCK) {
                if (poolSubmit == null || poolSubmit.isShutdown()) {
                    poolSubmit = new ThreadPoolExecutor(100, 500,
                            60L, TimeUnit.SECONDS,
                            new ArrayBlockingQueue(500), springThreadFactory);
                }
            }
        }
        return poolSubmit;
    }

    /**
     * 执行
     * @param task
     * @param <T>
     * @return
     */
    public static <T> Future<T> execute(Callable<T> task) {
        return poll().submit(task);
    }

    /**
     * submit
     * @param task
     * @return
     */
    public static Future<Boolean> submit(Callable task) {
        return poll().submit(task);
    }

    /**
     * Future
     * @param task
     * @return
     */
    public static Future<Object> submitFuture(Callable task) {
        return poll().submit(task);
    }

}
