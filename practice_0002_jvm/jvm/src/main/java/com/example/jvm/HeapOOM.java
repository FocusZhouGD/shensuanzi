package com.example.jvm;


import java.util.ArrayList;
import java.util.List;

/**
 * 造成GC堆OOM
 * 堆空间
 * -Xms20m (JVM初始分配的堆内存)-Xmx20m(最大可使用内存)
 * -XX:+HeapDumpOnOutOfMemoryError(r，JVM会在遇到OutOfMemoryError时拍摄一个“堆转储快照”)
 *
 * jvm启动参数: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list =new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }

    }


    /**
     * [GC (Allocation Failure)  5104K->2866K(19968K), 0.0060458 secs]
     * [GC (Allocation Failure)  8498K->6961K(19968K), 0.0101546 secs]
     * [GC (Allocation Failure)  12593K->12631K(19968K), 0.0133541 secs]
     * [Full GC (Ergonomics)  12631K->10639K(19968K), 0.3146966 secs]
     * [Full GC (Ergonomics)  16271K->14048K(19968K), 0.1964545 secs]
     * [Full GC (Ergonomics)  16579K->16506K(19968K), 0.2546556 secs]
     * [Full GC (Allocation Failure)  16506K->16488K(19968K), 0.2446132 secs]
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid31088.hprof ...
     * Heap dump file created [28355963 bytes in 0.136 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at com.example.jvm.HeapOOM.main(HeapOOM.java:22)
     */
    //Java虚拟机栈OOM
//    1.StackOverflowError,线程请求的栈深度大于虚拟机所允许的深度。
//    2.OutOfMemoryError栈扩展时申请到不足够的内存。


    //为了让JVM，更容易出现StackOverflowError
    //-Xss128k(设置每个线程的堆栈大小 为128K)


    //利用死递归  栈空间计算


}
