package com.springboot.dlock.starter.model;

import com.springboot.dlock.starter.enums.LockTypeEnum;

/**
 * @ClassName LockInfo
 * @Description 锁的基本信息
 * @Author zhouguodong
 * @Date 2021/12/15 15:14
 * @Version 1.0
 **/
public class LockInfo {
  private LockTypeEnum typeEnum;
  private String name;

    public LockTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(LockTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    private long waitTime;
  private long releaseTime;

    public LockInfo() {
    }

    public LockInfo(LockTypeEnum typeEnum, String name, long waitTime, long releaseTime) {
        this.typeEnum = typeEnum;
        this.name = name;
        this.waitTime = waitTime;
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "LockInfo{" +
                "typeEnum=" + typeEnum +
                ", name='" + name + '\'' +
                ", waitTime=" + waitTime +
                ", releaseTime=" + releaseTime +
                '}';
    }
}
