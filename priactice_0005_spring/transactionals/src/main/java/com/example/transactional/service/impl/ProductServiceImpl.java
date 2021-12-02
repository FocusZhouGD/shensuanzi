package com.example.transactional.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.transactional.entity.OrderInfo;
import com.example.transactional.entity.Product;
import com.example.transactional.mapper.OrderInfoMapper;
import com.example.transactional.mapper.ProductMapper;
import com.example.transactional.oneuntils.ThreadPoolUtil;
import com.example.transactional.service.ProductService;
import com.example.transactional.util.ThreadPollUtil;
import com.example.transactional.util.ThreadPools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private Lock lock = new ReentrantLock(true);

    @Transactional(rollbackFor = ArithmeticException.class)
    @Override
    public void sellProduct() {
        try {
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

            int a= 1/0;
        }catch (ArithmeticException e){
            System.out.println("yichang");
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void testForDeal() {

        List<String> list =new ArrayList<>();

//        ThreadPools.exec.submit(()->{
//            dealSave();
//        });

        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(32));

        executor.submit(() -> {
            dealSave();
        });
//        ThreadPoolUtil.poolSubmit().submit(()->{
//            dealSave();
//        });
    }




    @Transactional
    @Override
    public void sellProducts() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":抢到锁啦，进入方法");
            Product product = baseMapper.selectById(1);
            Long productCount = product.getProductCount();
            System.out.println(Thread.currentThread().getName() + ":当前库存 =" + productCount);
            if (productCount > 0) {
                product.setProductCount(productCount - 1);
                Integer integer = baseMapper.updateById(product);
                //创建订单数
                OrderInfo orderInfo =new OrderInfo();
                orderInfo.setBuyName(Thread.currentThread().getName());
                orderInfo.setBuyGoods(product.getProductName());
                orderInfoMapper.insert(orderInfo);
                System.out.println(Thread.currentThread().getName() + ":库存减少完毕，创建订单完毕！");
            } else {
                System.out.println(Thread.currentThread().getName() + "没有库存啦");
            }
            System.out.println(Thread.currentThread().getName()+":释放锁！");
        } finally {
            lock.unlock();
        }


    }

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public void sellRightProducts() {
        lock.lock();
        try {
            productService.sellNoLockProduct();
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void sellNoLockProduct() {
        System.out.println(Thread.currentThread().getName() + ":抢到锁啦，进入方法");
        Product product = baseMapper.selectById(1);
        Long productCount = product.getProductCount();
        System.out.println(Thread.currentThread().getName() + ":当前库存 =" + productCount);
        if (productCount > 0) {
            product.setProductCount(productCount - 1);
            Integer integer = baseMapper.updateById(product);
            //创建订单数
            OrderInfo orderInfo =new OrderInfo();
            orderInfo.setBuyName(Thread.currentThread().getName());
            orderInfo.setBuyGoods(product.getProductName());
            orderInfoMapper.insert(orderInfo);
            System.out.println(Thread.currentThread().getName() + ":库存减少完毕，创建订单完毕！");
        } else {
            System.out.println(Thread.currentThread().getName() + "没有库存啦");
        }
        System.out.println(Thread.currentThread().getName()+":释放锁！");
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
        for (int i = 1; i < 100; i++) {
            try {
                save(i);
            } catch (Exception e) {
                System.out.println("发生异常" + i);
                e.printStackTrace();
                continue;
            }
        }
    }

    private void save(int i) {
        Product product = new Product();
        if (i == 50) {
            JSONObject object = new JSONObject();
            long currentPoint = object.getLong("current_point");
            product.setProductCount(currentPoint);
        } else if (i == 80) {
            JSONObject object = new JSONObject();
            long currentPoint = object.getLong("current_point");
            product.setProductCount(currentPoint);
        } else {
            product.setProductCount((long) i);
            product.setProductName("测试" + i);
        }
        baseMapper.insert(product);
    }
}
