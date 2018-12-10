package com.sujian.sms.service;

import com.sujian.sms.commons.SmsException;
import com.sujian.sms.entity.User;

public abstract interface ISmsService {
  
  public abstract int saveUser(User paramUser) throws SmsException;
  
  public abstract int deleteUser(User paramUser) throws SmsException;
  
  public abstract int updateUser(User paramUser) throws SmsException;
  
  public abstract java.util.Collection<User> getUserByObject(User paramUser) throws SmsException;

  public abstract java.util.Collection<User> getUserByAll() throws SmsException;

}
