package com.example.transactional.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.Product;
import com.example.transactional.mapper.ProductMapper;
import com.example.transactional.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
