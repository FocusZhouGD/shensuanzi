# jvm
 * 自定义类加载器
 
 [参考资料](https://github.com/MarcusJiang1306/JAVA-000)
 
 [参考铁锚大佬](https://github.com/renfufei/JAVA-000)
 
 
 *生成GC日志
  
    java -Xloggc:gc.GCLogAnalysis.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.example.jvm.customclassload.gc.GCLogAnalysis
  
 * 模拟一下OOM
  
     1）模拟一下OOM，java -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
     
     2）分别使用512m,1024m,2048m,4096m,观察GC信息的不同
     
 *  Young GC  Full GC 和 minor GC （小型GC）  major GC （大型GC）
 
 * 串行GC UseSerialGC
 
 
    java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    
    java -XX:+UseSerialGC -Xmx512m -Xms512m -Xloggc:gc.gcloganalysis.useserialgc512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.example.jvm.customclassload.gc.GCLogAnalysis

   观察Young GC 和 Full GC
   
 * 并行GC
 
 
      java -XX:+UseParallelGC-Xms512m -Xmx512m-Xloggc:gc.demo.log-XX:+PrintGCDetails-XX:+PrintGCDateStampsGCLogAnalysis
      
      java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.gcloganalysis.useparallelgc512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.example.jvm.customclassload.gc.GCLogAnalysis
      
      
 * 不配置-Xms 会怎样？ 只配置-Xmx512m
    
    -Xms为-Xmx的一半
 * 不配置 -Xmx 会怎样？ 只配置 -Xms512m
   猜想 -Xmx默认1/4内存大小
     yes
 
 * CMS GC      
 
 
    java -XX:+UseConcMarkSweepGC-Xms512m -Xmx512m-Xloggc:gc.demo.log-XX:+PrintGCDetails-XX:+PrintGCDateStampsGCLogAnalysis   
     
    java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.gcloganalysis.useconcmarksweepgc512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.example.jvm.customclassload.gc.GCLogAnalysis
    

      
 
 观察Young GC 与Full GC
 
 思考：假如Xmx/Xms设置4g会怎么样？  如果都一样就会报错 
 
 -Xms4G 报错：Error occurred during initialization of VM
 -Xmx4G 正常
 
 4g内存下跟并行gc相比呢？
 
  并行gc出现一次Full GC 
  
  
 * G1 GC 
 
 
    java -XX:+UseG1GC-Xms512m -Xmx512m-Xloggc:gc.demo.log-XX:+PrintGCDetails-XX:+PrintGCDateStampsGCLogAnalysis
    
    java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.gc.gcloganalysis.useg1gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.example.jvm.customclassload.gc.GCLogAnalysis
    
    
 观察Young GC 与Full GC
    
 思考：假如Xmx/Xms设置4g会怎么样？
    
 4g内存下跟cms gc相比呢？ 
 
 
 
 JVM相关的常见面试问题汇总：
 
 1. 什么是JVM？ 
 
     1.1 请问JDK与JVM有什么区别？ 
     1.2 你认识哪些JVM厂商? 
     1.3 OracleJDK与OpenJDK有什么区别? 
     1.4 开发中使用哪个版本的JDK？生产环境呢? 为什么这么选？
 
  2. 什么是Java字节码？ 
 
     2.1 字节码文件中包含哪些内容? 
     
     2.2 什么是常量? 
          java中的常量有整数常量，浮点型常量，布尔常量，字符常量。
     2.3 你怎么理解常量池?
           常量池有class常量池，运行时常量池，String常量池 
 
 3. JVM的运行时数据区有哪些? 
 
     3.1 什么是堆内存？
        堆内存是所有线程共用的内存空间，对象的存储
     3.2 堆内存包括哪些部分？ 
         堆内存分为年轻代和老年代，年轻代分为新生代（Eden）、存活区（Survivor）[S0和S1]
     3.3 什么是非堆内存? 
         存储加载的类的和元数据
          
 4. 什么是内存溢出？
     申请内存的时候，内存不够了 ，容易OOM
 
     4.1 什么是内存泄漏? 
     内存泄露：申请的内存空间没有被正确释放，导致后续程序里这块内存被永远占用(占着茅坑)
     4.2 两者有什么关系？ 
 5. 给定一个具体的类，请分析对象的内存占用 
 
 
     5.1 怎么计算出来的? 
     5.2 对象头中包含哪些部分？
 6. 常用的JVM启动参数有哪些? 
 
     6.1 设置堆内存XMX应该考虑哪些因素？ 
         考虑非堆内存和堆外内存（有时候可能会和堆内存一样大），必要时需要降低数值
     6.2 假设物理内存是8G，设置多大堆内存比较合适? 
          70%
     6.3 ­Xmx 设置的值与JVM进程所占用的内存有什么关系? 
     
     6.4 怎样开启GC日志？ 
          -Xloggc：gc.log -XX:+PrintGCDetails -XX:+PrintGCDataStamps
     6.5 请指定使用G1垃圾收集器来启动Hello程序 
          -XX:+UseG1GC
 7. Java8默认使用的垃圾收集器是什么? 
        并行GC
        Java 9 默认垃圾收集器是G1        
     7.1 Java11的默认垃圾收集器是什么?
        
     7.2 常见的垃圾收集器有哪些? 
         
     7.3 什么是串行垃圾收集? 
     7.4 什么是并行垃圾收集? 
     7.5 什么是并发垃圾收集器? 
     7.6 什么是增量式垃圾收集? 
     7.7 什么是年轻代？ 
     7.8 什么是GC停顿(GC pause)? 
     7.9 GC停顿与STW停顿有什么区别？
  8. 如果CPU使用率突然飙升，你会怎么排查？
 top 、jstack 、jmap 、jcmd
 
     8.1 如果系统响应变慢，你会怎么排查？ 
     8.2 系统性能一般怎么衡量？ 
 9. 使用过哪些JVM相关的工具？ 
 
     9.1 查看JVM进程号的命令是什么?
     jps
     9.2 怎么查看剩余内存? 
     free 
     9.3 查看线程栈的工具是什么？ 
     jstack
     9.4 用什么工具来获取堆内存转储? 
     
     9.5 内存Dump时有哪些注意事项? 
     9.6 使用JMAP转储堆内存大致的参数怎么处理？ 
     9.7 为什么转储文件以 .hprof 结尾? 
     9.8 内存Dump完成之后，用什么工具来分析? 
     9.9 如果忘记了使用什么参数你一般怎么处理? 
 
 10. 开发性问题：你碰到过哪些JVM问题？
 
 
 
 
 * 使用JMX注册GC事件监控 
   
   [参考资料](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/JVM%20%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF%2032%20%E8%AE%B2%EF%BC%88%E5%AE%8C%EF%BC%89/32%20%E5%BA%94%E5%AF%B9%E5%AE%B9%E5%99%A8%E6%97%B6%E4%BB%A3%E9%9D%A2%E4%B8%B4%E7%9A%84%E6%8C%91%E6%88%98%EF%BC%9A%E9%95%BF%E9%A3%8E%E7%A0%B4%E6%B5%AA%E4%BC%9A%E6%9C%89%E6%97%B6%E3%80%81%E7%9B%B4%E6%8C%82%E4%BA%91%E5%B8%86%E6%B5%8E%E6%B2%A7%E6%B5%B7.md)
      