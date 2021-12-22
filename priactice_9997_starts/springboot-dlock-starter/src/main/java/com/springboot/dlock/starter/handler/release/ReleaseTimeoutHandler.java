package com.springboot.dlock.starter.handler.release;

import java.lang.management.LockInfo;

/**
 * @ClassName ReleaseTimeoutHandler
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/15 15:09
 * @Version 1.0
 **/
public interface ReleaseTimeoutHandler {
    void handle(LockInfo lockInfo);
}
