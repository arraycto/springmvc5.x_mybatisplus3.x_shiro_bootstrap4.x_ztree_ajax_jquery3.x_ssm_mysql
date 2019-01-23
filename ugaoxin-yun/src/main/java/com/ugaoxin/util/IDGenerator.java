package com.ugaoxin.util;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.aliyun.oss.common.utils.DateUtil;
import com.baomidou.mybatisplus.toolkit.Sequence;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: IDGenerator.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class IDGenerator {  //雪花算法 ，融合mybatisplus
    
	  //主机的机器码
	  private static final Sequence worker = new Sequence();
	
	  private static long num=0; 
	    
	  //返回Long
	  public static  long  getId(){
		  return worker.nextId();
	  }
	  
	  /**
	   * 随机生成UUID
	   * @return
	   */
	  public static synchronized String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	  }
	  /**
	   * 根据字符串生成固定UUID
	   * @param name
	   * @return
	   */
	  public static synchronized String getUUID(String name){
	    UUID uuid=UUID.nameUUIDFromBytes(name.getBytes());
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	  }
	  /**
	   * 根据日期生成长整型id
	   * @param args
	   */
	  public static synchronized long getLongId(){
		  
		  return new Date().getTime()/1000;   //获取当前时间的秒数值  
		  }
	}