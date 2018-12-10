package com.sujian.sms.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sujian.sms.entity.User;

public class BaseController {

  // 获取session对象
  @Autowired
  public HttpSession session;

  // 登录用户key
  protected static final String HAVE_LOGIN_USER = "HAVE_LOGIN_USER_KEY";

  // 创建基本页面
  protected ModelAndView createBasePage(String viewName) throws SmsException {

    // chuangjian一个页面
    ModelAndView page = new ModelAndView(viewName);
    page = addWebContext(page);

    /**
     * 加入登录用户信息
     */
    try {
      if (viewName.equals("login") || viewName.equals("register")) {
        return page;
      } else {
        if (this.session.getAttribute(HAVE_LOGIN_USER) != null) {
          page.addObject("LOGIN_USER", this.session.getAttribute(HAVE_LOGIN_USER));
        } else {
          throw new SmsException("登录超时，请重新登录！");
        }
      }
    } catch (SmsException e) {
      ModelAndView login = new ModelAndView("login");
      login = addWebContext(login);
      login.addObject("errorMessage", e.getMessage());
      return login;
    }
    /**
     * 加入MenuActive
     */
    if (viewName.contains("index")) {
      page.addObject("active", "index");
    }
    // if (viewName.contains("classroom")) {
    // page.addObject("active", "classroom");
    // }
    // if (viewName.contains("student")) {
    // page.addObject("active", "student");
    // }
    // if (viewName.contains("subject")) {
    // page.addObject("active", "subject");
    // }
    // if (viewName.contains("score")) {
    // page.addObject("active", "score");
    // }
    // if (viewName.contains("calculate")) {
    // page.addObject("active", "calculate");
    // }
    return page;
  }

  private ModelAndView addWebContext(ModelAndView page) {
    /**
     * 加入WebContext
     */
    // 获取request对象
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    // 判断请求路径中是否含有“：”
    if (!request.getRequestURL().toString().contains(":")) { // 如果无 分号 则为域名
      page.addObject("contextPath", "");
    } else {
      page.addObject("contextPath", request.getContextPath()); // 有 则为调试
    }

    return page;
  }

  protected User getLoginUser() {
    try {
      User user = (User) this.session.getAttribute(HAVE_LOGIN_USER);
      if (user != null) {
        return user;
      } else {
        throw new SmsException("用户登录超时，请重新登录！");
      }
    } catch (SmsException e) {
      throw e;
    }

  }

}
