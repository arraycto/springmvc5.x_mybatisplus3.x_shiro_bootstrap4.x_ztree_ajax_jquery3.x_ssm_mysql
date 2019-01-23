package com.ugaoxin.history;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Copyright © Array老师. All rights reserved.
 * @Title: SysHistory.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.history
 * @Description: 记录系统日志或者操作信息
 * @author: Array老师 
 * @version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface SysHistory {
  String value() default "";
}
