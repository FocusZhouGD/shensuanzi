package com.example.tools.mqcovert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CovertMessageUtil {



    /**
     * 解析消息
     *
     * @param body
     * @return
     */
    public static OrderRemarkDTO convertOrderRemarkMsg(byte[] body) {
        String messageBody = new String(body, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return objectMapper.readValue(messageBody, OrderRemarkDTO.class);
        } catch (IOException e) {
            log.error("convert bytes to OrderRemarkMsg exception!", e);
        }
        return null;
    }
}
