# jvm
 * 自定义类加载器
 
 [参考资料](https://github.com/MarcusJiang1306/JAVA-000)
 
 [参考铁锚大佬](https://github.com/renfufei/JAVA-000)
 
 
 
 
 [GC (Allocation Failure) [PSYoungGen: 64286K->10742K(75264K)] 64286K->21800K(247296K), 0.0037172 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 [GC (Allocation Failure) [PSYoungGen: 75192K->10744K(139776K)] 86250K->41830K(311808K), 0.0060778 secs] [Times: user=0.03 sys=0.09, real=0.01 secs]
 [GC (Allocation Failure) [PSYoungGen: 139768K->10738K(139776K)] 170854K->88328K(311808K), 0.0078697 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 [GC (Allocation Failure) [PSYoungGen: 139762K->10745K(268800K)] 217352K->129455K(440832K), 0.0066340 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 [Full GC (Ergonomics) [PSYoungGen: 10745K->0K(268800K)] [ParOldGen: 118710K->114070K(254464K)] 129455K->114070K(523264K), [Metaspace: 2647K->2647K(1056768K)], 0.0156512 secs] [Times: user=0.02 sys=0.02, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 257973K->10748K(268800K)] 372043K->190844K(523264K), 0.0110009 secs] [Times: user=0.05 sys=0.08, real=0.01 secs]
 [Full GC (Ergonomics) [PSYoungGen: 10748K->0K(268800K)] [ParOldGen: 180095K->169774K(369152K)] 190844K->169774K(637952K), [Metaspace: 2647K->2647K(1056768K)], 0.0206677 secs] [Times: user=0.13 sys=0.00, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 258048K->87517K(484864K)] 427822K->257689K(854016K), 0.0232305 secs] [Times: user=0.06 sys=0.19, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 484829K->103420K(512512K)] 655001K->346074K(881664K), 0.0218076 secs] [Times: user=0.08 sys=0.05, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 512508K->157695K(728064K)] 755162K->431262K(1097216K), 0.0228024 secs] [Times: user=0.05 sys=0.08, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 728063K->198654K(769024K)] 1001630K->517917K(1138176K), 0.0278521 secs] [Times: user=0.03 sys=0.09, real=0.03 secs]
 [Full GC (Ergonomics) [PSYoungGen: 198654K->0K(769024K)] [ParOldGen: 319262K->320905K(557056K)] 517917K->320905K(1326080K), [Metaspace: 2647K->2647K(1056768K)], 0.0542834 secs] [Times: user=0.44 sys=0.02, real=0.06 secs]
 [GC (Allocation Failure) [PSYoungGen: 570368K->161114K(1024000K)] 891273K->482020K(1581056K), 0.0223660 secs] [Times: user=0.02 sys=0.09, real=0.02 secs]
 [GC (Allocation Failure) [PSYoungGen: 934746K->196140K(1031168K)] 1255652K->596832K(1588224K), 0.0331996 secs] [Times: user=0.14 sys=0.06, real=0.03 secs]
 [GC (Allocation Failure) [PSYoungGen: 969772K->188857K(1076736K)] 1370464K->686258K(1633792K), 0.0381423 secs] [Times: user=0.16 sys=0.08, real=0.04 secs]
 [Full GC (Ergonomics) [PSYoungGen: 188857K->0K(1076736K)] [ParOldGen: 497401K->372794K(666112K)] 686258K->372794K(1742848K), [Metaspace: 2647K->2647K(1056768K)], 0.0423487 secs] [Times: user=0.34 sys=0.03, real=0.04 secs]
 执行结束!共生成对象次数:16733
 Heap
  PSYoungGen      total 1076736K, used 32041K [0x000000076c300000, 0x00000007c0000000, 0x00000007c0000000)
   eden space 801792K, 3% used [0x000000076c300000,0x000000076e24a728,0x000000079d200000)
   from space 274944K, 0% used [0x00000007af380000,0x00000007af380000,0x00000007c0000000)
   to   space 285696K, 0% used [0x000000079d200000,0x000000079d200000,0x00000007ae900000)
  ParOldGen       total 666112K, used 372794K [0x00000006c4800000, 0x00000006ed280000, 0x000000076c300000)
   object space 666112K, 55% used [0x00000006c4800000,0x00000006db40ebb0,0x00000006ed280000)
  Metaspace       used 2653K, capacity 4486K, committed 4864K, reserved 1056768K
   class space    used 291K, capacity 386K, committed 512K, reserved 1048576K