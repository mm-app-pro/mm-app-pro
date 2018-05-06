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
import com.xjy.enums.OrderStatusEnum;
import com.xjy.service.WorkerService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.JsonUtils;
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
    public RespList<OrderRecord> isCheckedOrder(HttpServletRequest req, PageBean page) {// 查看已经审核完的单
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
        logger.info("Invoke isCheckedOrder end!result:{}",JsonUtils.toJsonString(list.getResult()));
        return result;
    }

    @RequestMapping("getOrder")
    public RespBody getOrder(HttpServletRequest req, OrderRecord record) {// 领取
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

    @RequestMapping("getValidOrder")
    @ResponseBody
    public RespList<OrderRecord> getValidOrder(HttpServletRequest req, PageBean page) {// 获取自己的订单
        logger.info("Invoke getValidOrder start!");
        SysUser user = (SysUser) req.getSession().getAttribute("user");
        Page<OrderRecord> list = workerService.listAllOrderByJobNum(page.getPageNum(),
                page.getPageSize(), user.getNum(), OrderStatusEnum.VALID.name());
        RespList<OrderRecord> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("Invoke getValidOrder end!");
        return result;
    }

    @RequestMapping("getDetailById")
    @ResponseBody
    public OrderRecord getDetailById(OrderRecord record) {// 通过order id 获取工单记录详情
        logger.info("Invoke getDetailById start!");
        OrderRecord result = workerService.selectOrderRecordById(record.getId());
        logger.info("Invoke getDetailById end!");
        return result;
    }

    @RequestMapping("getFinishOrder")
    @ResponseBody
    public RespList<OrderRecord> getFinishOrder(HttpServletRequest req, PageBean page) {// 获取自己的订单
        logger.info("Invoke getFinishOrder start!");
        SysUser user = (SysUser) req.getSession().getAttribute("user");
        Page<OrderRecord> list = workerService.listAllOrderByJobNum(page.getPageNum(),
                page.getPageSize(), user.getNum(), OrderStatusEnum.FINISH.name());
        RespList<OrderRecord> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("Invoke getFinishOrder end!");
        return result;
    }

    @RequestMapping("finishOrder")
    @ResponseBody
    public RespBody finishOrder(OrderRecord record) {// 更新为已完成状态
        logger.info("Invoke finish order start!");
        RespBody resp = new RespBody();
        try {
            logger.info("reocrd:{}", record);
            workerService.finishOrder(record.getId(), record.getRemark());
            resp.setCode(0);
            resp.setMessage("success!");
        } catch (Exception ex) {
            logger.error("finish order error! e:{}", ex.getMessage());
            resp.setCode(1);
            resp.setMessage("server error!");
        }
        logger.info("Invoke finish order end!");
        return resp;
    }
}
