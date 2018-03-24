package com.xjy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xjy.entity.OrderRecord;
import com.xjy.entity.SysUser;
import com.xjy.enums.OrderStatusEnum;
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
    public SysUser pageApplyOrder(HttpServletRequest req) {
        logger.info("Invoke loginUser start!");
        SysUser su = (SysUser) req.getSession().getAttribute("user");
        logger.info("Invoke loginUser end!");
        return su;
    }

    @RequestMapping("submit")
    @ResponseBody
    public RespBody submitOrder(HttpServletRequest req, OrderRecord record) {
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
    public List<OrderRecord> listRecordByIndetityNum(HttpServletRequest req) {
        SysUser su = (SysUser) req.getSession().getAttribute("user");
        String identityNum = su.getNum();
        logger.info("Invoke list start! data:{}", identityNum);
        List<OrderRecord> list = applyOrderService.listByIdentityNum(identityNum);
        logger.info("Invoke list end! list:{}", list);
        return list;
    }
}
