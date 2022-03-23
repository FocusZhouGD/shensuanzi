# 基准测试

* [资料](https://mp.weixin.qq.com/s/k6t7yJLx73dWov835XVy3A)

#线程池


* 队列
ArrayBlockingQueue 有界队列，可以有效
LinkedBlockingQueue 无界队列


* 拒绝策略

1、AbortPolicy 直接拒绝并抛出异常策略

2、DiscardPolicy 抛弃任务策略

3、DiscardLastPolicy 抛弃最先消息策略

4、CallerRunsPolicy 由调用者所在的线程执行该任务

* 四种创建方式

* dubbo中线程池原理
1、 IO处理线程池（使用netty）
2、 服务调用线程池




#同步锁、显示锁
* 三大性：原子性、可见性、顺序性

事务特性：原子性、一致性、隔离性、持久性。



* Synchronized 和ReentrantLock

1、使用场景不一样
2、ReentrantLock默认公平锁， Synchronized非公平


Synchronized 
3、底层实现不一样  Synchronized 是jvm层面的 底层实现是monitorenter和monitorenexit  ；ReentrantLock是api层面的

4、是否可以释放

Synchronized可以自动释放， private Lock lock = new ReentrantLock();要手动释放，lock.lock unlock,结合try、final使用


5、是否可以中断
Synchronized 不可以中断，和ReentrantLock可以中断，通过超时时间 trylock(long timeout,TimeUnit unit)


6、锁是否可以绑定条件

 ConcurrentHashMap 1.7采用分段锁 
 1.8使用 CAS和 Synchronized  跟数据结构有关
 




  

