package com.sujian.sms.persistent;

import com.sujian.sms.commons.SmsException;
import com.sujian.sms.entity.User;

public abstract interface IUserPersistent {
  /**
   * 
   * @author sujian
   * @return 
   *
   */
  public abstract int saveUser(User paramUser) throws SmsException;
  
  public abstract int deleteUser(User paramUser) throws SmsException;
  
  public abstract int UpdateUser(User paramUser) throws SmsException;
  
  public abstract User getUserByPrimaryKey(String paramString) throws SmsException;
  
  public abstract java.util.Collection<User> getUserByObject(User paramUser) throws SmsException;
  
  public abstract java.util.Collection<User> getUserByAll() throws SmsException;
  
}
