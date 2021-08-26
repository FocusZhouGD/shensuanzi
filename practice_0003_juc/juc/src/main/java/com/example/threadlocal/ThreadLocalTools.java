package com.example.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTools {

    static Logger log = LoggerFactory.getLogger(ThreadLocalTools.class);

    public static final ThreadLocal<List<DataBuffer>> LOCAL = new ThreadLocal<>();


    public static void addToThreadLocal(DataBuffer buff) {
        if (buff == null) {
            return;
        }
        List<DataBuffer> dataBuffers = LOCAL.get();
        if (dataBuffers == null) {
            dataBuffers = new ArrayList<>();
            LOCAL.set(dataBuffers);
        }
        dataBuffers.add(buff);
    }

    public static void releaseBuff() {
        List<DataBuffer> dataBuffers = LOCAL.get();
        if (dataBuffers == null || dataBuffers.size() == 0) {
            LOCAL.remove();
            return;
        }
        for (DataBuffer dataBuffer : dataBuffers) {
            DataBufferUtils.release(dataBuffer);
        }
        LOCAL.remove();
    }


    public static boolean isExistsBuffer(DataBuffer buffer) {
        List<DataBuffer> dataBuffers = LOCAL.get();
        if (dataBuffers == null || dataBuffers.size() == 0) {
            return false;
        }
        boolean isEqual = false;
        for (DataBuffer item : dataBuffers) {
            if (item == buffer) {
                isEqual = true;
                break;
            }
        }
        if (isEqual) {
            log.info("相等 ====");
        } else {
            log.info("不相等 ===");
        }
        return isEqual;
    }

}
