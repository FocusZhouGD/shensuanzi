package com.example.transactional.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.Product;
import com.example.transactional.mapper.ProductMapper;
import com.example.transactional.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private Lock lock = new ReentrantLock(true);

    @Transactional
    @Override
    public void sellProduct() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ":进入方法");
        Product product = baseMapper.selectById(1);
        Long productCount = product.getProductCount();
        System.out.println(Thread.currentThread().getName() + ":当前库存 =" + productCount);
        if (productCount > 0) {
            product.setProductCount(productCount - 1);
            Integer integer = baseMapper.updateById(product);
            System.out.println(Thread.currentThread().getName() + ":库存减少完毕，创建订单");
        } else {
            System.out.println(Thread.currentThread().getName() + "没有库存啦");
        }

        lock.unlock();
    }
}
