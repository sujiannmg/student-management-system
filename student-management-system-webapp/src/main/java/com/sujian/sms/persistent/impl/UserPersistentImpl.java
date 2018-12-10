package com.sujian.sms.persistent.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.sujian.sms.commons.SmsException;
import com.sujian.sms.commons.SqlBuilder;
import com.sujian.sms.entity.User;
import com.sujian.sms.persistent.IUserPersistent;

@org.springframework.stereotype.Repository("UserPersistent")
public class UserPersistentImpl implements IUserPersistent {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @javax.annotation.Resource
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  private static final String TABLE_NAME = "SMS_USER"; // 数据库表名
  private static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<String>();
  private static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<String>();

  // 数据库字段和程序内变量对应集合
  private static final LinkedHashMap<String, String> PARAMETER_COLUMNS = new LinkedHashMap<String, String>();

  // 基本SQL语句操作
  private static String SQL_BASE_ADD; // 增加操作
  private static String SQL_BASE_DELETE; // 删除操作
  private static String SQL_BASE_UPDATE; // 修改操作
  private static String SQL_BASE_SELECT; // 查询操作

  // 静态代码段
  static {
    COLUMNS.add("USER_ID");
    COLUMNS.add("ACCOUNT");
    COLUMNS.add("PASSWORD");

    PARAMETER_COLUMNS.put("USER_ID", "userId");
    PARAMETER_COLUMNS.put("ACCOUNT", "account");
    PARAMETER_COLUMNS.put("PASSWORD", "password");

    PRIMARY_KEY.add("USER_ID");

    SQL_BASE_ADD = SqlBuilder.getAdd(TABLE_NAME, COLUMNS, PARAMETER_COLUMNS);
    SQL_BASE_DELETE = SqlBuilder.getDelete(TABLE_NAME);
    SQL_BASE_UPDATE = SqlBuilder.getUpdate(TABLE_NAME, PARAMETER_COLUMNS);
    SQL_BASE_SELECT = SqlBuilder.getSelect(TABLE_NAME, COLUMNS);

  }

  @Override
  public int saveUser(User paramUser) throws SmsException {
    try {
      paramUser.setUserId(UUID.randomUUID().toString());
      return namedParameterJdbcTemplate.update(SQL_BASE_ADD, new BeanPropertySqlParameterSource(paramUser));
    } catch (SmsException e) {
      throw new SmsException("创建用户失败：服务器持久化错误");
    } catch (Exception e) {
      e.printStackTrace();
      throw new SmsException("创建用户失败：数据库服务器错误");
    }
  }

  @Override
  public int deleteUser(User paramUser) throws SmsException {
    StringBuilder sql = new StringBuilder(SQL_BASE_DELETE);
    sql.append("AND USER_ID = :userId");
    try {
      return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(paramUser));
    } catch (SmsException e) {
      throw new SmsException("删除用户失败：服务器持久化错误");
    } catch (Exception e) {
      throw new SmsException("删除用户失败：数据库服务器错误");
    }
  }

  @Override
  public int UpdateUser(User paramUser) throws SmsException {
    StringBuilder sql = new StringBuilder(SQL_BASE_UPDATE);
    sql.append("AND USER_ID = :userId");
    try {
      return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(paramUser));
    } catch (SmsException e) {
      throw new SmsException("修改用户失败：服务持久化错误");
    } catch (Exception e) {
      throw new SmsException("修改用户失败：数据库服务错误");
    }
  }

  @Override
  public User getUserByPrimaryKey(String paramString) throws SmsException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<User> getUserByObject(User paramUser) throws SmsException {
    StringBuilder sql = new StringBuilder(SQL_BASE_SELECT);
    if (paramUser.getUserId() != null) {
      sql.append("AND USER_ID = :userId");
    }
    if (paramUser.getAccount() != null) {
      sql.append("AND ACCOUNT = :account");
    }
    if (paramUser.getPassword() != null) {
      sql.append("AND PASSWORD = :password");
    }
    try {
      Collection<User> userList = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(paramUser), BeanPropertyRowMapper.newInstance(User.class));
      return userList;
    } catch (SmsException e) {
      e.printStackTrace();
      throw new SmsException("查询用户失败：服务器持久化错误");
    } catch (Exception e) {
      e.printStackTrace();
      throw new SmsException("查询用户失败：数据库服务器错误");
    }
  }

  @Override
  public Collection<User> getUserByAll() throws SmsException {
    try{
      Collection<User> userList = namedParameterJdbcTemplate.query(SQL_BASE_SELECT, BeanPropertyRowMapper.newInstance(User.class));
      return userList;
    }catch(SmsException e){
      e.printStackTrace();
      throw new SmsException("获取用户信息失败：服务器持久化错误");
    }catch(Exception e){
      e.printStackTrace();
      throw new SmsException("获取用户信息失败：数据库服务器错误");
    }
  }

}
