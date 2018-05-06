package com.xjy.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.Page;
import com.xjy.entity.OrderRecord;
import com.xjy.entity.PageBean;
import com.xjy.entity.SysUser;
import com.xjy.service.SysUserManageService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.RespBody;
import com.xjy.util.RespList;

@Controller
@RequestMapping("user")
public class SysUserManageController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserManageController.class);

    @Resource
    private SysUserManageService sysUserManageService;

    @RequestMapping("html")
    public ModelAndView html() {
        ModelAndView mv = new ModelAndView("manager/manager");
        return mv;
    }

    @RequestMapping("list")
    @ResponseBody
    public RespList<SysUser> listSysUser(PageBean page) {
        logger.info("Invoke listSysUser start!");
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 1;
        }
        Page<SysUser> list = sysUserManageService.listSysUser(pageNum, pageSize);
        RespList<SysUser> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("result:{}", result);
        logger.info("Invoke listSysUser end!");
        return result;
    }

    @RequestMapping("uncheckOrder")
    @ResponseBody
    public RespList<OrderRecord> uncheckOrder(PageBean page, String status) {// 按照状态查找
        logger.info("Invoke uncheckOrder start!");
        if (StringUtils.isBlank(status)) {
            status = null;
        }
        Page<OrderRecord> list = sysUserManageService.listUncheckOrder(page.getPageNum(),
                page.getPageSize(), status);
        RespList<OrderRecord> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("Invoke uncheckOrder end!");
        return result;
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
    public RespBody addSysUser(HttpServletRequest req, SysUser user) {
        logger.info("Invoke addSysUser start!");
        RespBody resp = new RespBody();

        SysUser session = (SysUser) req.getSession().getAttribute("user");// 判断是否有管理员权限
        if (!session.getType().equals("admin")) {
            resp.setCode(1);
            resp.setMessage("not competence!");
            return resp;
        }

        try {
            SysUser record = sysUserManageService.selectUserById(user.getId());
            if (null == record) {
                sysUserManageService.addSysUser(user);
            } else {
                sysUserManageService.modifyUser(user);
            }
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
    public RespBody modifySysUser(HttpServletRequest req, SysUser user) {
        logger.info("Invoke modifySysUser start!");
        RespBody resp = new RespBody();
        try {
            sysUserManageService.modifyUser(user);
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
    public RespBody modifyStatus(HttpServletRequest req, SysUser user) {
        logger.info("Invoke modifyStatus start!user:{}", user);
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

    @RequestMapping("checkOrder")
    @ResponseBody
    public RespBody checkOrder(OrderRecord record) {
        logger.info("Invoke checkOrder start!");
        RespBody resp = new RespBody();
        try {
            sysUserManageService.checkOrder(record);
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
            logger.error("error! e:{}", ex.getMessage());
        }
        logger.info("Invoke checkOrder end!resp:{}", resp);
        return resp;
    }

    @RequestMapping("selectUserRecord")
    @ResponseBody
    public SysUser selectSysUserRecord(Integer id) {
        logger.info("Invoke selectSysUserRecord start!id:{}", id);
        SysUser user = sysUserManageService.selectUserById(id);
        logger.info("Invoke selectSysUserRecord end!user:{}", user);
        return user;
    }

    @RequestMapping("findWorker")
    @ResponseBody
    public List<SysUser> findWorkerByType(OrderRecord record) {
        logger.info("Invoke findWorkerByType start!");
        record = sysUserManageService.findRecordById(record.getId());
        List<SysUser> list = sysUserManageService.listWorkerByType(record.getType());
        logger.info("Invoke findWorkerByType end!user:{}", record);
        return list;
    }

}
