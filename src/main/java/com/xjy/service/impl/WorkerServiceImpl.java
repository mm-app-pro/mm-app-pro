package com.xjy.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xjy.dao.OrderRecordMapper;
import com.xjy.entity.OrderRecord;
import com.xjy.enums.OrderStatusEnum;
import com.xjy.service.WorkerService;
import com.xjy.util.BusinessServiceException;

@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
    private static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Resource
    private OrderRecordMapper orderRecordMapper;

    @Override
    public Page<OrderRecord> listIsCheckedOrder(Integer pageNum, Integer pageSize) {
        logger.info("Invoke listIsCheckedOrder start!");
        Page<OrderRecord> list = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            orderRecordMapper.listIsCheckedOrder();
        });
        logger.info("Invoke listIsCheckedOrder end!");
        return list;
    }

    @Override
    public int getOrderByOrderId(Integer orderId, String jobNum, String jobName)
            throws BusinessServiceException {
        logger.info("Invoke getOrderByOrderId start!");
        int result = orderRecordMapper.getOrderByOrderId(orderId, jobNum, jobName);
        if (result == 0) {
            throw new BusinessServiceException("领取失败");
        }
        logger.info("Invoke getOrderByOrderId end!");
        return result;
    }

    @Override
    public Page<OrderRecord> listAllOrderByJobNum(Integer pageNum, Integer pageSize, String jobNum,
            String status) {
        logger.info("Invoke listAllOrderByJobNum start!");
        Page<OrderRecord> list = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            orderRecordMapper.listAllOrderByJobNum(jobNum, status);
        });
        logger.info("Invoke listAllOrderByJobNum end!");
        return list;
    }

    @Override
    public int finishOrder(Integer orderId) {
        OrderRecord record = new OrderRecord();
        record.setId(orderId);
        record.setStatus(OrderStatusEnum.FINISH.name());
        record.setModifyTime(new Date());
        return orderRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public OrderRecord selectOrderRecordById(Integer id) {
        logger.info("Invoke selectOrderRecordById start!");
        OrderRecord record = orderRecordMapper.selectByPrimaryKey(id);
        logger.info("Invoke selectOrderRecordById end!");
        return record;
    }


}
