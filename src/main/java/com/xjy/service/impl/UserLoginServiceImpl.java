package com.xjy.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xjy.dao.SysUserMapper;
import com.xjy.entity.SysUser;
import com.xjy.service.UserLoginService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.Md5Utils;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser login(SysUser user) throws BusinessServiceException {
        logger.info("Invoke login start!");
        if (null == user) {
            throw new BusinessServiceException("无效参数");
        }
        user.setPassword(Md5Utils.encrypt(user.getPassword()));
        logger.info("password:{}",user.getPassword());
        SysUser login =
                sysUserMapper.checkLogin(user.getNum(), user.getPassword(), user.getRoleId());
        if (null == login) {
            throw new BusinessServiceException("用户不存在或密码不正确");
        }
        logger.info("Invoke login end!");
        return login;
    }

}
