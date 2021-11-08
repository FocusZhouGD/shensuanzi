package com.example.transactional;

import com.example.transactional.entity.OrderInfo;
import com.example.transactional.service.OrderInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TransactionalApplicationTests {
    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    void contextLoads() {
    }


    @Test
    void testOrder(){
        OrderInfo orderInfo =new OrderInfo();
        orderInfo.setBuyName("测试");
        orderInfo.setBuyGoods("ipad");
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfoService.insert(orderInfo);
    }


}
