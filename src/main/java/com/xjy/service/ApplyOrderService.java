package com.xjy.service;

import com.github.pagehelper.Page;
import com.xjy.entity.OrderRecord;
import com.xjy.util.BusinessServiceException;

public interface ApplyOrderService {
    void submit(OrderRecord record) throws BusinessServiceException;

    Page<OrderRecord> listByIdentityNum(String identityNum, Integer pageNum, Integer pageSize);
}
