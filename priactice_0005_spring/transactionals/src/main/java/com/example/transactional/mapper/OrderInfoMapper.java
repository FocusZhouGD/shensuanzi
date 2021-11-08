package com.example.transactional.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.transactional.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zgd
 * @since 2021-11-08
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    @Select("select * from order_info")
    List<OrderInfo> selectAll();

}
