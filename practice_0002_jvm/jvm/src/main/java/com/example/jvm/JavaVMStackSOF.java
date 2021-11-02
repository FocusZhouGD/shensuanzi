package com.example.jvm;

/**
 * -Xss128k(设置每个线程的堆栈大小 为128K)
 *
 * java 虚拟机栈OOM
 * 虚拟机栈理论上有2种异常：
 *   1.StackOverflowError,线程请求的栈深度大于虚拟机所允许的深度。
 *   2.OutOfMemoryError栈扩展时申请到不足够的内存。
 *   为了让JVM，更容易出现StackOverflowError
 *   -Xss128k(设置每个线程的堆栈大小 为128K)。
 *
 *
 *   JVM参数： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -Xss128k -XX:+PrintGC
 *
 */
public class JavaVMStackSOF {


    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF stackSOF =new JavaVMStackSOF();
        stackSOF.stackLeak();
    }


    /**
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     * 	at com.example.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:20)
     */
}
