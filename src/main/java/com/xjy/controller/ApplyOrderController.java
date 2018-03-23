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

@Controller
@RequestMapping("apply")
public class ApplyOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyOrderController.class);
    @Resource
    private ApplyOrderService applyOrderService;

    @RequestMapping("page")
    public ModelAndView pageApplyOrder(HttpServletRequest req) {
        logger.info("Invoke page start!");
        SysUser su = (SysUser) req.getSession().getAttribute("user");
        if (null == su) {
            return new ModelAndView("redirect:/login/page");
        }
        ModelAndView mv = new ModelAndView("index");
//        List<Map<String, String>> typeList = new ArrayList<>();
//        OrderStatusEnum[] ose = OrderStatusEnum.values();
//        for (OrderStatusEnum e : ose) {
//            Map<String, String> map = new HashMap<>();
//            map.put(e.name(), e.getName());
//            typeList.add(map);
//        }
//        mv.addObject("type", JsonUtils.toJsonString(typeList));
        mv.addObject("identityNum", su.getNum());
        mv.addObject("name", su.getName());
        mv.addObject("mobile", su.getMobile());
        logger.info("data:{}", mv.getModel());
        logger.info("Invoke page end!");
        return mv;
    }

    @RequestMapping("submit")
    @ResponseBody
    public RespBody submitOrder(HttpServletRequest req, @RequestBody OrderRecord record) {
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
//        String identityNum = su.getNum();
        String identityNum = "1234";
        logger.info("Invoke list start! data:{}", identityNum);
        List<OrderRecord> list = applyOrderService.listByIdentityNum(identityNum);
        logger.info("Invoke list end! list:{}", list);
        return list;
    }
}
