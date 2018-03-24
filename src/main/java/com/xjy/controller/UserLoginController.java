package com.xjy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xjy.entity.SysRole;
import com.xjy.entity.SysUser;
import com.xjy.service.SysRoleService;
import com.xjy.service.UserLoginService;
import com.xjy.util.BusinessServiceException;
import com.xjy.util.RespBody;

@Controller
@RequestMapping("login")
public class UserLoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Resource
    private UserLoginService userLoginService;
    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("page")
    public ModelAndView userLoginView() {
        logger.info("Invoke userLoginView start!");
        ModelAndView mv = new ModelAndView("login");
        logger.info("Invoke userLoginView end!");
        return mv;
    }

    @RequestMapping("user")
    @ResponseBody
    public RespBody userLogin(HttpServletRequest req, SysUser user) {
        logger.info("Invoke userLogin start!");
        RespBody resp = new RespBody();
        // user.setRoleId(3);
        try {
            SysUser login = userLoginService.login(user);
            resp.setCode(0);
            resp.setMessage("登陆成功");
            req.getSession().setAttribute("user", login);
        } catch (BusinessServiceException e) {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
        }
        logger.info("Invoke userLogin end!");
        return resp;
    }

}
