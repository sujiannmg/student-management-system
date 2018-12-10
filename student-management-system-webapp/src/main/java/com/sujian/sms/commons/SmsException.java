package com.sujian.sms.commons;
/**
 * 
 * @author sujian
 *
 */
public class SmsException extends RuntimeException{

  /**
   * serialVersionUID 用来表明类的不同版本间的兼容性
   */
  private static final long serialVersionUID = 1L;
  
  public SmsException(String errorMessage) {
    super(errorMessage);
  }

}
