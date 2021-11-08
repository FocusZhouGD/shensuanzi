package com.example.transactional.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.transactional.entity.Product;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {



}
