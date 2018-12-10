package com.sujian.sms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sujian.sms.commons.BaseController;
import com.sujian.sms.service.ISmsService;

@Controller("LoginController")
public class LoginController extends BaseController {

  @Resource(name = "SmsService")
  public ISmsService smsService;

  // 获取登录页面
  @RequestMapping(value = "login", method = RequestMethod.GET)
  public ModelAndView login() {
    return super.createBasePage("login");
  }

}
