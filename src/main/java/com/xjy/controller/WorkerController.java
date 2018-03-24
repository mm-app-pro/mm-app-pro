package com.xjy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.xjy.service.WorkerService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.RespBody;
import com.xjy.util.RespList;

@Controller
@RequestMapping("worker")
public class WorkerController {
    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    @Resource
    private WorkerService workerService;

    @RequestMapping("html")
    public ModelAndView isCheckedOrderPage(PageBean page) {
        ModelAndView mv = new ModelAndView("serviceman/serviceman");
        return mv;
    }

    @RequestMapping("isCheckedOrder")
    @ResponseBody
    public RespList<OrderRecord> isCheckedOrder(HttpServletRequest req, PageBean page) {
        logger.info("Invoke isCheckedOrder start!");
        Page<OrderRecord> list =
                workerService.listIsCheckedOrder(page.getPageNum(), page.getPageSize());
        RespList<OrderRecord> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("Invoke isCheckedOrder end!");
        return result;
    }

    @RequestMapping("getOrder")
    public RespBody getOrder(HttpServletRequest req, OrderRecord record) {// 抢单
        logger.info("Invoke getOrder start!");
        RespBody resp = new RespBody();
        SysUser user = (SysUser) req.getSession().getAttribute("user");
        try {
            workerService.getOrderByOrderId(record.getId(), user.getNum(), user.getName());
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (BusinessServiceException e) {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke getOrder end!");
        return resp;
    }

    @RequestMapping("getAllOrder")
    public ModelAndView getAllOrder(HttpServletRequest req, PageBean page) {// 获取自己的订单
        logger.info("Invoke getAllOrder start!");
        ModelAndView mv = new ModelAndView();
        SysUser user = (SysUser) req.getSession().getAttribute("user");
        Page<OrderRecord> list = workerService.listAllOrderByJobNum(page.getPageNum(),
                page.getPageSize(), user.getNum());
        mv.addObject("list", list.getResult());
        mv.addObject("pageNum", list.getPageNum());
        mv.addObject("pageSize", list.getPageSize());
        mv.addObject("pages", list.getPages());
        mv.addObject("total", list.getTotal());
        logger.info("Invoke getAllOrder end!");
        return mv;
    }

    @RequestMapping("finishOrder") // 已完成
    @ResponseBody
    public RespBody finishOrder(OrderRecord record) {
        logger.info("Invoke finish order start!");
        RespBody resp = new RespBody();
        try {
            workerService.finishOrder(record.getId());
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (Exception ex) {
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke finish order end!");
        return resp;
    }
}
