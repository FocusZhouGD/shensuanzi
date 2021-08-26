Classfile /D:/code/codeproject/shensuanzi/practice_0003_juc/juc/src/main/java/com/example/conc/Counter.class
  Last modified 2021-8-26; size 1696 bytes
  MD5 checksum 0d3083f25261dfe03ebdbc0b945ec927
  Compiled from "Counter.java"
public class com.example.conc.Counter
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #16.#41        // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#42         // com/example/conc/Counter.sum:I
   #3 = Class              #43            // com/example/conc/Counter
   #4 = Methodref          #3.#41         // com/example/conc/Counter."<init>":()V
   #5 = Methodref          #3.#44         // com/example/conc/Counter.incr:()V
   #6 = Class              #45            // java/lang/Thread
   #7 = InvokeDynamic      #0:#50         // #0:run:(ILcom/example/conc/Counter;)Ljava/lang/Runnable;
   #8 = Methodref          #6.#51         // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
   #9 = InvokeDynamic      #1:#50         // #1:run:(ILcom/example/conc/Counter;)Ljava/lang/Runnable;
  #10 = Methodref          #6.#53         // java/lang/Thread.start:()V
  #11 = Long               1000l
  #13 = Methodref          #6.#54         // java/lang/Thread.sleep:(J)V
  #14 = Class              #55            // java/lang/InterruptedException
  #15 = Methodref          #14.#56        // java/lang/InterruptedException.printStackTrace:()V
  #16 = Class              #57            // java/lang/Object
  #17 = Utf8               B
  #18 = Utf8               I
  #19 = Utf8               ConstantValue
  #20 = Integer            10
  #21 = Utf8               sum
  #22 = Utf8               <init>
  #23 = Utf8               ()V
  #24 = Utf8               Code
  #25 = Utf8               LineNumberTable
  #26 = Utf8               incr
  #27 = Utf8               getNum
  #28 = Utf8               ()I
  #29 = Utf8               main
  #30 = Utf8               ([Ljava/lang/String;)V
  #31 = Utf8               StackMapTable
  #32 = Class              #43            // com/example/conc/Counter
  #33 = Class              #58            // "[Ljava/lang/String;"
  #34 = Class              #45            // java/lang/Thread
  #35 = Class              #55            // java/lang/InterruptedException
  #36 = Utf8               lambda$main$1
  #37 = Utf8               (ILcom/example/conc/Counter;)V
  #38 = Utf8               lambda$main$0
  #39 = Utf8               SourceFile
  #40 = Utf8               Counter.java
  #41 = NameAndType        #22:#23        // "<init>":()V
  #42 = NameAndType        #21:#18        // sum:I
  #43 = Utf8               com/example/conc/Counter
  #44 = NameAndType        #26:#23        // incr:()V
  #45 = Utf8               java/lang/Thread
  #46 = Utf8               BootstrapMethods
  #47 = MethodHandle       #6:#59         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #48 = MethodType         #23            //  ()V
  #49 = MethodHandle       #6:#60         // invokestatic com/example/conc/Counter.lambda$main$0:(ILcom/example/conc/Counter;)V
  #50 = NameAndType        #61:#62        // run:(ILcom/example/conc/Counter;)Ljava/lang/Runnable;
  #51 = NameAndType        #22:#63        // "<init>":(Ljava/lang/Runnable;)V
  #52 = MethodHandle       #6:#64         // invokestatic com/example/conc/Counter.lambda$main$1:(ILcom/example/conc/Counter;)V
  #53 = NameAndType        #65:#23        // start:()V
  #54 = NameAndType        #66:#67        // sleep:(J)V
  #55 = Utf8               java/lang/InterruptedException
  #56 = NameAndType        #68:#23        // printStackTrace:()V
  #57 = Utf8               java/lang/Object
  #58 = Utf8               [Ljava/lang/String;
  #59 = Methodref          #69.#70        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #60 = Methodref          #3.#71         // com/example/conc/Counter.lambda$main$0:(ILcom/example/conc/Counter;)V
  #61 = Utf8               run
  #62 = Utf8               (ILcom/example/conc/Counter;)Ljava/lang/Runnable;
  #63 = Utf8               (Ljava/lang/Runnable;)V
  #64 = Methodref          #3.#72         // com/example/conc/Counter.lambda$main$1:(ILcom/example/conc/Counter;)V
  #65 = Utf8               start
  #66 = Utf8               sleep
  #67 = Utf8               (J)V
  #68 = Utf8               printStackTrace
  #69 = Class              #73            // java/lang/invoke/LambdaMetafactory
  #70 = NameAndType        #74:#78        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #71 = NameAndType        #38:#37        // lambda$main$0:(ILcom/example/conc/Counter;)V
  #72 = NameAndType        #36:#37        // lambda$main$1:(ILcom/example/conc/Counter;)V
  #73 = Utf8               java/lang/invoke/LambdaMetafactory
  #74 = Utf8               metafactory
  #75 = Class              #80            // java/lang/invoke/MethodHandles$Lookup
  #76 = Utf8               Lookup
  #77 = Utf8               InnerClasses
  #78 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #79 = Class              #81            // java/lang/invoke/MethodHandles
  #80 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #81 = Utf8               java/lang/invoke/MethodHandles
{
  public static final int B;
    descriptor: I
    flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL
    ConstantValue: int 10

  public com.example.conc.Counter();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_0
         6: putfield      #2                  // Field sum:I
         9: return
      LineNumberTable:
        line 3: 0
        line 5: 4

  public synchronized void incr();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: aload_0
         2: getfield      #2                  // Field sum:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field sum:I
        10: return
      LineNumberTable:
        line 8: 0
        line 9: 10

  public int getNum();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field sum:I
         4: ireturn
      LineNumberTable:
        line 12: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=7, args_size=1
         0: sipush        10000
         3: istore_1
         4: new           #3                  // class com/example/conc/Counter
         7: dup
         8: invokespecial #4                  // Method "<init>":()V
        11: astore_2
        12: iconst_0
        13: istore_3
        14: iload_3
        15: iload_1
        16: if_icmpge     29
        19: aload_2
        20: invokevirtual #5                  // Method incr:()V
        23: iinc          3, 1
        26: goto          14
        29: new           #3                  // class com/example/conc/Counter
        32: dup
        33: invokespecial #4                  // Method "<init>":()V
        36: astore_3
        37: new           #6                  // class java/lang/Thread
        40: dup
        41: iload_1
        42: aload_3
        43: invokedynamic #7,  0              // InvokeDynamic #0:run:(ILcom/example/conc/Counter;)Ljava/lang/Runnable;
        48: invokespecial #8                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
        51: astore        4
        53: new           #6                  // class java/lang/Thread
        56: dup
        57: iload_1
        58: aload_3
        59: invokedynamic #9,  0              // InvokeDynamic #1:run:(ILcom/example/conc/Counter;)Ljava/lang/Runnable;
        64: invokespecial #8                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
        67: astore        5
        69: aload         4
        71: invokevirtual #10                 // Method java/lang/Thread.start:()V
        74: aload         5
        76: invokevirtual #10                 // Method java/lang/Thread.start:()V
        79: ldc2_w        #11                 // long 1000l
        82: invokestatic  #13                 // Method java/lang/Thread.sleep:(J)V
        85: goto          95
        88: astore        6
        90: aload         6
        92: invokevirtual #15                 // Method java/lang/InterruptedException.printStackTrace:()V
        95: return
      Exception table:
         from    to  target type
            79    85    88   Class java/lang/InterruptedException
      LineNumberTable:
        line 16: 0
        line 17: 4
        line 18: 12
        line 19: 19
        line 18: 23
        line 22: 29
        line 23: 37
        line 28: 53
        line 33: 69
        line 34: 74
        line 36: 79
        line 39: 85
        line 37: 88
        line 38: 90
        line 42: 95
      StackMapTable: number_of_entries = 4
        frame_type = 254 /* append */
          offset_delta = 14
          locals = [ int, class com/example/conc/Counter, int ]
        frame_type = 250 /* chop */
          offset_delta = 14
        frame_type = 255 /* full_frame */
          offset_delta = 58
          locals = [ class "[Ljava/lang/String;", int, class com/example/conc/Counter, class com/example/conc/Counter, class java/lang/Thread, class java/lang/Thread ]
          stack = [ class java/lang/InterruptedException ]
        frame_type = 6 /* same */
}
SourceFile: "Counter.java"
InnerClasses:
     public static final #76= #75 of #79; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #47 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #48 ()V
      #49 invokestatic com/example/conc/Counter.lambda$main$0:(ILcom/example/conc/Counter;)V
      #48 ()V
  1: #47 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #48 ()V
      #52 invokestatic com/example/conc/Counter.lambda$main$1:(ILcom/example/conc/Counter;)V
      #48 ()V
      
#######################################################################################分割线###############################################################
#同步代码块 synchronize

* $\color{#FF0000}{红}$
 
 $\color{#FF7D00}{橙}$ $\color{#FF0000}{黄}$ $\color{#00FF00}{绿}$  $\color{#0000FF}{蓝}$ $\color{#00FFFF}{靛}$ $\color{#FF00FF}{紫}$ 


Classfile /D:/code/codeproject/shensuanzi/practice_0003_juc/juc/src/main/java/com/example/conc/CounterSynchronize.class
  Last modified 2021-8-26; size 496 bytes
  MD5 checksum 365f192e9f6e3e3604cd1cc2f6a5b351
  Compiled from "CounterSynchronize.java"
public class com.example.conc.CounterSynchronize
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#19         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#20         // com/example/conc/CounterSynchronize.sum:I
   #3 = Class              #21            // com/example/conc/CounterSynchronize
   #4 = Class              #22            // java/lang/Object
   #5 = Utf8               sum
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               incr
  #12 = Utf8               test
  #13 = Utf8               StackMapTable
  #14 = Class              #21            // com/example/conc/CounterSynchronize
  #15 = Class              #22            // java/lang/Object
  #16 = Class              #23            // java/lang/Throwable
  #17 = Utf8               SourceFile
  #18 = Utf8               CounterSynchronize.java
  #19 = NameAndType        #7:#8          // "<init>":()V
  #20 = NameAndType        #5:#6          // sum:I
  #21 = Utf8               com/example/conc/CounterSynchronize
  #22 = Utf8               java/lang/Object
  #23 = Utf8               java/lang/Throwable
{
  public com.example.conc.CounterSynchronize();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_0
         6: putfield      #2                  // Field sum:I
         9: return
      LineNumberTable:
        line 6: 0
        line 8: 4

  public synchronized void incr();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: aload_0
         2: getfield      #2                  // Field sum:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field sum:I
        10: return
      LineNumberTable:
        line 11: 0
        line 12: 10

  public void test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter
         4: aload_0
         5: aload_0
         6: getfield      #2                  // Field sum:I
         9: iconst_2
        10: iadd
        11: putfield      #2                  // Field sum:I
        14: aload_1
        15: monitorexit
        16: goto          24
        19: astore_2
        20: aload_1
        21: monitorexit
        22: aload_2
        23: athrow
        24: return
      Exception table:
         from    to  target type
             4    16    19   any
            19    22    19   any
      LineNumberTable:
        line 15: 0
        line 16: 4
        line 17: 14
        line 18: 24
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 19
          locals = [ class com/example/conc/CounterSynchronize, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4
}
SourceFile: "CounterSynchronize.java"  



* 总结：synchronize在方法上 底层会使用ACC_SYNCHRONIZED指令，如果在代码块中会使用  monitorenter和monitorexit指令
      
      
      
      
