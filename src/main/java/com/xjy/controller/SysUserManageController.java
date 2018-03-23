package com.xjy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.xjy.entity.PageBean;
import com.xjy.entity.SysUser;
import com.xjy.service.SysUserManageService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.RespBody;

@Controller
@RequestMapping("user")
public class SysUserManageController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserManageController.class);

    @Resource
    private SysUserManageService sysUserManageService;

    @RequestMapping("list")
    public ModelAndView listSysUser(@RequestBody PageBean page) {
        logger.info("Invoke listSysUser start!");
        ModelAndView mv = new ModelAndView("");
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 1;
        }
        Page<SysUser> list = sysUserManageService.listSysUser(pageNum, pageSize);
        mv.addObject("list", list.getResult());
        mv.addObject("pageNum", list.getPageNum());
        mv.addObject("pageSize", list.getPageSize());
        mv.addObject("pages", list.getPages());
        mv.addObject("total", list.getTotal());
        logger.info("Invoke listSysUser end!");
        return mv;
    }

    @RequestMapping("modifyPage")
    public ModelAndView modifyPage(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("");
        SysUser session = (SysUser) req.getSession().getAttribute("user");
        mv.addObject("user", session);
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public RespBody addSysUser(HttpServletRequest req, @RequestBody SysUser user) {
        logger.info("Invoke addSysUser start!");
        RespBody resp = new RespBody();

        SysUser session = (SysUser) req.getSession().getAttribute("user");// 判断是否有管理员权限
        if (!session.getType().equals("admin")) {
            resp.setCode(1);
            resp.setMessage("not competence!");
            return resp;
        }

        try {
            sysUserManageService.addSysUser(user);
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (BusinessServiceException e) {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke addSysUser end!");
        return resp;
    }

    @RequestMapping("modify")
    @ResponseBody
    public RespBody modifySysUser(HttpServletRequest req, @RequestBody SysUser user) {
        logger.info("Invoke modifySysUser start!");
        RespBody resp = new RespBody();
        try {
            sysUserManageService.addSysUser(user);
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (BusinessServiceException e) {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke modifySysUser end!");
        return resp;
    }

    @RequestMapping("modifyStatus")
    @ResponseBody
    public RespBody modifyStatus(HttpServletRequest req, @RequestBody SysUser user) {
        logger.info("Invoke modifyStatus start!");
        SysUser session = (SysUser) req.getSession().getAttribute("user");
        RespBody resp = new RespBody();
        if (!session.getType().equals("admin")) {
            resp.setCode(1);
            resp.setMessage("not competence!");
            return resp;
        }

        try {
            Integer id = user.getId();
            String status = user.getStatus();
            sysUserManageService.modifyStatus(id, status);
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (BusinessServiceException e) {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke modifyStatus end!");
        return resp;
    }

}
