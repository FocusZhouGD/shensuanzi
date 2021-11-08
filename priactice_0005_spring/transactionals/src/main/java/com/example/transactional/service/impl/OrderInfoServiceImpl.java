package com.example.transactional.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.OrderInfo;
import com.example.transactional.mapper.OrderInfoMapper;
import com.example.transactional.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    @Override
    public int selectAll() {
        List<OrderInfo> orderInfos = orderInfoMapper.selectAll();
        System.out.println("db result"+orderInfos);
        return orderInfoMapper.selectAll().size();
    }


}
