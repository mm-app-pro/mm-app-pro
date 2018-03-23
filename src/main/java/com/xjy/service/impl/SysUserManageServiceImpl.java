package com.xjy.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xjy.dao.SysUserMapper;
import com.xjy.entity.SysUser;
import com.xjy.service.SysUserManageService;
import com.xjy.util.BusinessServiceException;

@Service("sysUserManageService")
public class SysUserManageServiceImpl implements SysUserManageService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserManageServiceImpl.class);

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public void addSysUser(SysUser user) throws BusinessServiceException {
        logger.info("Invoke addSysUser start!");
        if (null == user) {
            throw new BusinessServiceException("params can not be null!");
        }
        String maxNum = sysUserMapper.selectMaxNum(); // 取当前工号最大值
        if (StringUtils.isBlank(maxNum)) {
            maxNum = "100001";
        } else {
            maxNum = String.valueOf(Integer.parseInt(maxNum) + 1);
        }

        user.setNum(maxNum);
        user.setPassword("12345");// 默认密码

        sysUserMapper.insertSelective(user);
        logger.info("Invoke addSysUser end!");
    }

    @Override
    public void modifyUser(SysUser user) throws BusinessServiceException {
        logger.info("Invoke modifyUser start!");
        if (null == user) {
            throw new BusinessServiceException("params can not be null!");
        }
        sysUserMapper.updateByPrimaryKeySelective(user);
        logger.info("Invoke modifyUser end!");
    }

    @Override
    public Page<SysUser> listSysUser(Integer pageNum, Integer pageSize) {
        logger.info("Invoke listSysUser start!");
        Page<SysUser> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            sysUserMapper.listSysUser();
        });
        logger.info("Invoke listSysUser end!");
        return page;
    }

    @Override
    public void modifyStatus(Integer id, String status) throws BusinessServiceException {
        logger.info("Invoke modifyStatus start!");
        if (null == id || StringUtils.isBlank(status)) {
            throw new BusinessServiceException("params can not be null!");
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        sysUserMapper.updateByPrimaryKeySelective(user);
        logger.info("Invoke modifyStatus end!");

    }

}
