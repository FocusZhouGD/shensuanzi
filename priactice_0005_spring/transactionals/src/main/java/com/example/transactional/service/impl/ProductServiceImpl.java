package com.example.transactional.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.Product;
import com.example.transactional.mapper.ProductMapper;
import com.example.transactional.oneuntils.ThreadPoolUtil;
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

    @Override
    public void testForDeal() {
//        ThreadPollUtil.execute(()->{
//            dealSave();
//        });
        ThreadPoolUtil.poolSubmit().submit(()->{
            dealSave();
        });
    }

    /**
     * 这种做法会阻塞for循环 线上会
     */
   /* private void dealSave() {
        for (int i=1;i<100;i++) {
            save(i);
        }
    }*/

    /**
     * 要加try
     */
    private void dealSave() {
        for (int i=1;i<100;i++){
            try {
                save(i);
            } catch (Exception e) {
                System.out.println("发生异常"+i);
                e.printStackTrace();
                continue;
            }
        }
    }

    private void save(int i) {
        Product product =new Product();
        if (i==50){
            JSONObject object =new JSONObject();
            long currentPoint = object.getLong("current_point");
            product.setProductCount(currentPoint);
        }else {
            product.setProductCount((long) i);
            product.setProductName("测试"+i);
        }
        baseMapper.insert(product);
    }
}
