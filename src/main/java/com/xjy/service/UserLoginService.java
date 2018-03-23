package com.xjy.service;

import com.xjy.entity.SysUser;
import com.xjy.util.BusinessServiceException;

public interface UserLoginService {
    public SysUser login(SysUser user) throws BusinessServiceException;
}
