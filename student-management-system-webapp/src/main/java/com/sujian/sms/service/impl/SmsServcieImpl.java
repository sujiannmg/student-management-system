package com.sujian.sms.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sujian.sms.commons.SmsException;
import com.sujian.sms.entity.User;
import com.sujian.sms.persistent.IUserPersistent;
import com.sujian.sms.service.ISmsService;

@Service("SmsService")
public class SmsServcieImpl implements ISmsService {

  @Resource(name = "UserPersistent")
  private IUserPersistent userPersistent;

  @Override
  public int saveUser(User paramUser) throws SmsException {
    User searchUser = new User();
    searchUser.setUserId(paramUser.getUserId());
    try {
      if (userPersistent.getUserByObject(searchUser).isEmpty()) {
        return userPersistent.saveUser(paramUser);
      } else {
        throw new SmsException("用户创建失败：此帐号" + paramUser.getAccount() + "已经存在");
      }
    } catch (SmsException e) {
      throw e;
    }
  }

  @Override
  public int deleteUser(User paramUser) throws SmsException {
    return userPersistent.deleteUser(paramUser);
  }

  @Override
  public int updateUser(User paramUser) throws SmsException {
    return userPersistent.UpdateUser(paramUser);
  }

  @Override
  public Collection<User> getUserByObject(User paramUser) throws SmsException {
    return userPersistent.getUserByObject(paramUser);
  }

  @Override
  public Collection<User> getUserByAll() throws SmsException {
    return userPersistent.getUserByAll();
  }

}
