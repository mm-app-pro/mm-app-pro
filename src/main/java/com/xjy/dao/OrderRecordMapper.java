package com.xjy.dao;

import com.xjy.entity.OrderRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRecord record);

    int insertSelective(OrderRecord record);

    OrderRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRecord record);

    int updateByPrimaryKey(OrderRecord record);

    List<OrderRecord> listByIdentityNum(@Param("identityNum") String identityNum);

    List<OrderRecord> listIsCheckedOrder();

    int getOrderByOrderId(@Param("orderId") Integer orderId, @Param("jobNum") String jobNum,
            @Param("jobName") String jobName);

    List<OrderRecord> listAllOrderByJobNum(@Param("jobNum") String jobNum,
            @Param("status") String status);

    List<OrderRecord> listUncheckOrder(@Param("status") String status);
}
