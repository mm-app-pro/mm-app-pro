package com.xjy.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.xjy.dao.OrderRecordMapper;
import com.xjy.entity.OrderRecord;
import com.xjy.enums.OrderStatusEnum;
import com.xjy.service.ApplyOrderService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.UuidUtils;

@Service
public class ApplyOrderServiceImpl implements ApplyOrderService {
    private static final Logger logger = LoggerFactory.getLogger(ApplyOrderServiceImpl.class);

    @Resource
    private OrderRecordMapper orderRecordMapper;

    @Override
    public void submit(OrderRecord record) throws BusinessServiceException {
        logger.info("Invoke submit start!");
        if (null == record) {
            throw new BusinessServiceException("data can not be null!");
        }
        record.setOrderId(UuidUtils.create());
        record.setStatus(OrderStatusEnum.WAITECHECK.name());
        Date date = new Date();
        record.setCreateTime(date);
        record.setModifyTime(date);
        logger.info("data:{}", record);
        orderRecordMapper.insertSelective(record);
        logger.info("Invoke submit end!");
    }

    @Override
    public List<OrderRecord> listByIdentityNum(String identityNum) {
        logger.info("Invoke listByIdentityNum start!");
        if (StringUtils.isBlank(identityNum)) {
            return null;
        }
        List<OrderRecord> list = orderRecordMapper.listByIdentityNum(identityNum);
        logger.info("Invoke listByIdentityNum end!");
        return list;
    }


}
