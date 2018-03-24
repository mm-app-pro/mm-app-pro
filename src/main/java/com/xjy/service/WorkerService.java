package com.xjy.service;

import com.github.pagehelper.Page;
import com.xjy.entity.OrderRecord;
import com.xjy.util.BusinessServiceException;

public interface WorkerService {
    Page<OrderRecord> listIsCheckedOrder(Integer pageNum, Integer pageSize);

    void getOrderByOrderId(Integer orderId, String jobNum, String jobName)
            throws BusinessServiceException;

    Page<OrderRecord> listAllOrderByJobNum(Integer pageNum, Integer pageSize, String jobNum);

    int finishOrder(Integer orderId);
}
