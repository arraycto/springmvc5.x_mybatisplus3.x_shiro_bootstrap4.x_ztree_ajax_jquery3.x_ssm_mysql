package com.ugaoxin.util;

import java.io.Serializable;
 
/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunResult.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class YunResult implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 返回状态码 */
	private Integer code; // -1：参数不对；0:错误类提示；1：成功类
	
	/** 返回信息 */
	private String message;
	
	/** 返回数据 */
	private Object data;
	
	public YunResult(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public YunResult(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public YunResult() {
		 super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
