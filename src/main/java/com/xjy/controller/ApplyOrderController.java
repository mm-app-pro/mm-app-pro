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
import com.xjy.service.ApplyOrderService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.JsonUtils;
import com.xjy.util.RespBody;
import com.xjy.util.RespList;

@Controller
@RequestMapping("apply")
public class ApplyOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyOrderController.class);
    @Resource
    private ApplyOrderService applyOrderService;

    @RequestMapping("html")
    public ModelAndView html() {
        ModelAndView mv = new ModelAndView("student/student");
        return mv;
    }

    @RequestMapping("loginUser")
    @ResponseBody
    public SysUser loginUser(HttpServletRequest req) {// 用户登陆后获取用户信息
        logger.info("Invoke loginUser start!");
        SysUser su = (SysUser) req.getSession().getAttribute("user");
        logger.info("Invoke loginUser end!");
        return su;
    }

    @RequestMapping("submit")
    @ResponseBody
    public RespBody submitOrder(HttpServletRequest req, OrderRecord record) {// 工单提交
        logger.info("Invoke submit start!");
        RespBody rb = new RespBody();
        logger.info("data:{}", record);
        try {
            applyOrderService.submit(record);
            rb.setCode(0);
            rb.setMessage("success!");
        } catch (BusinessServiceException e) {
            rb.setCode(1);
            rb.setMessage(e.getMessage());
            return rb;
        } catch (Exception e) {
            rb.setCode(1);
            rb.setMessage("server error!");
            logger.error("e:{}", e.getMessage());
            return rb;
        }
        logger.info("Invoke submit end!");
        return rb;
    }

    @RequestMapping("list")
    @ResponseBody
    public RespList<OrderRecord> listRecordByIndetityNum(HttpServletRequest req, PageBean page) {// 查看当前用户以往工单
        SysUser su = (SysUser) req.getSession().getAttribute("user");
        String identityNum = su.getNum();
        logger.info("Invoke list start! data:{}", identityNum);
        Page<OrderRecord> list = applyOrderService.listByIdentityNum(identityNum, page.getPageNum(),
                page.getPageSize());
        RespList<OrderRecord> result = new RespList<>();
        result.setEndRow(list.getEndRow());
        result.setPageNum(list.getPageNum());
        result.setPages(list.getPages());
        result.setPageSize(list.getPageSize());
        result.setResult(list.getResult());
        result.setStartRow(list.getStartRow());
        result.setTotal(list.getTotal());
        logger.info("Invoke list end! list:{}", JsonUtils.toJsonString(list.getResult()));
        return result;
    }
}
