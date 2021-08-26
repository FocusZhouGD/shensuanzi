package com.example.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    final Lock locks = new ReentrantLock();
    final Condition notFull = locks.newCondition();
    final Condition notEmpty = locks.newCondition();

}
