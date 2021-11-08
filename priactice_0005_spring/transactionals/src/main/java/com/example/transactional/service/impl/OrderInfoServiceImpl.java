package com.example.transactional.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.OrderInfo;
import com.example.transactional.mapper.OrderInfoMapper;
import com.example.transactional.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
