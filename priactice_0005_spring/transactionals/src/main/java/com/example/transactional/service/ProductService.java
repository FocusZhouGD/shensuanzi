package com.example.transactional.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.transactional.entity.Product;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
public interface ProductService extends IService<Product> {

    void sellProduct();

    void testForDeal();

    void sellProducts();

}
