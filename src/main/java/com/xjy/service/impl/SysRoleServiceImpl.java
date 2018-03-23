package com.xjy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xjy.dao.SysRoleMapper;
import com.xjy.entity.SysRole;
import com.xjy.service.SysRoleService;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> listSysRole() {
        logger.info("Invoke listSysRole start!");
        List<SysRole> list = sysRoleMapper.listSysRole();
        logger.info("Invoke listSysRole end!");
        return list;
    }


}
