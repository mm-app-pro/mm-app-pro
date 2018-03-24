package com.xjy.dao;

import com.xjy.entity.SysUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> listSysUser();

    String selectMaxNum();

    SysUser checkLogin(@Param("num") String num, @Param("psw") String password,
            @Param("roleId") Integer roleId);
    
    List<SysUser> listWorkerByType(@Param("type") String type);
}
