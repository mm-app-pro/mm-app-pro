package com.xjy.service;

import com.github.pagehelper.Page;
import com.xjy.entity.OrderRecord;
import com.xjy.util.BusinessServiceException;

public interface WorkerService {
    Page<OrderRecord> listIsCheckedOrder(Integer pageNum, Integer pageSize);

    int getOrderByOrderId(Integer orderId, String jobNum, String jobName)
            throws BusinessServiceException;

    Page<OrderRecord> listAllOrderByJobNum(Integer pageNum, Integer pageSize, String jobNum,
            String status);

    int finishOrder(Integer orderId, String remark);

    OrderRecord selectOrderRecordById(Integer id);
}
