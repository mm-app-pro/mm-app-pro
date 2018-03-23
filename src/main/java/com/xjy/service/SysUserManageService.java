package com.xjy.service;

import com.github.pagehelper.Page;
import com.xjy.entity.SysUser;
import com.xjy.util.BusinessServiceException;

public interface SysUserManageService {
    void addSysUser(SysUser user) throws BusinessServiceException;

    void modifyUser(SysUser user) throws BusinessServiceException;

    Page<SysUser> listSysUser(Integer pageNum, Integer pageSize);

    void modifyStatus(Integer id, String status) throws BusinessServiceException;
}
