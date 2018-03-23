package com.xjy.service;

import java.util.List;

import com.xjy.entity.OrderRecord;
import com.xjy.util.BusinessServiceException;

public interface ApplyOrderService {
    void submit(OrderRecord record) throws BusinessServiceException;

    List<OrderRecord> listByIdentityNum(String identityNum);
}
