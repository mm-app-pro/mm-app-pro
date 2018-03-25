package com.xjy.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xjy.entity.OrderRecord;
import com.xjy.entity.SysUser;
import com.xjy.util.BusinessServiceException;

public interface SysUserManageService {
    void addSysUser(SysUser user) throws BusinessServiceException;

    void modifyUser(SysUser user) throws BusinessServiceException;

    Page<SysUser> listSysUser(Integer pageNum, Integer pageSize);

    void modifyStatus(Integer id, String status) throws BusinessServiceException;

    Page<OrderRecord> listUncheckOrder(Integer pageNum, Integer pageSize);

    List<SysUser> listWorkerByType(String type);

    int checkOrder(OrderRecord record);
    
    OrderRecord findRecordById(Integer id);

}
