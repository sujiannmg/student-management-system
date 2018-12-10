package com.sujian.sms.commons;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * 用于生成基本SQL语句
 *@author sujian
 *
 */
public class SqlBuilder {

  //拼接一个基本INSERT语句
  public static String getAdd(String TABLE_NAME,LinkedHashSet<String> COLUMNS ,LinkedHashMap<String ,String> PARAMETER_COLUMNS){
    StringBuilder SQL_BASE = new StringBuilder();
    SQL_BASE.append(" INSERT INTO ").append(TABLE_NAME);
    SQL_BASE.append(" ( ");
    boolean first = true;
    for(String column : COLUMNS){
      if(first){
        SQL_BASE.append(column);
        first = false;
      }else{
        SQL_BASE.append(" , ").append(column);
      }
    }
    SQL_BASE.append(" ) ");
    SQL_BASE.append(" VALUES (");
    first = true;
    for(String column : PARAMETER_COLUMNS.values()){
      if(first){
        SQL_BASE.append(" :").append(column);
        first = false;
      }else{
        SQL_BASE.append(" , :").append(column);
      }
    }
    SQL_BASE.append(" ) ");
    return SQL_BASE.toString();
  }
  
  //拼接一个基本UPDATE语句
  public static String getUpdate(String TABLE_NAME ,LinkedHashMap<String ,String> PARAMETER_COLUMNS){
    StringBuilder SQL_BASE = new StringBuilder();
    SQL_BASE.append(" UPDATE ").append(TABLE_NAME);
    SQL_BASE.append(" SET ");
    boolean first = true;
    for(String key : PARAMETER_COLUMNS.keySet()){
      if(first){
        SQL_BASE.append(key).append(" = :").append(PARAMETER_COLUMNS.get(key));
        first = false;
      }else{
        SQL_BASE.append(" , ").append(key).append(" = :").append(PARAMETER_COLUMNS.get(key));
      }
    }
    SQL_BASE.append(" WHERE 1=1 ");
    return SQL_BASE.toString();
  }
  
  //拼接一个基本DELETE语句
  public static String getDelete(String TABLE_NAME){
    StringBuilder SQL_BASE = new StringBuilder();
    SQL_BASE.append(" DELETE FROM ").append(TABLE_NAME);
    SQL_BASE.append(" WHERE 1=1 ");
    return SQL_BASE.toString();
  }
  
  //拼接一个基本SELECT语句
  public static String getSelect(String TABLE_NAME, LinkedHashSet<String> COLUMNS ){
    StringBuilder SQL_BASE = new StringBuilder();
    SQL_BASE.append(" SELECT  ");
    boolean first = true;
    for(String column : COLUMNS){
      if(first){
        SQL_BASE.append(column);
        first = false;
      }else{
        SQL_BASE.append(" , ").append(column);
      }
    }
    SQL_BASE.append(" FROM ").append(TABLE_NAME);
    SQL_BASE.append(" WHERE 1=1 ");
    return SQL_BASE.toString();
  }
}
