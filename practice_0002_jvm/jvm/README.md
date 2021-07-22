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
 
   