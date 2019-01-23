package com.ugaoxin.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.ugaoxin.enums.SexEnum;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: StringToEnumConverterFactory.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public final class StringToEnumConverterFactory implements Converter<String, SexEnum>{

	  
	public SexEnum convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		} 
		return   SexEnum.valueOfCode(Integer.valueOf(source));

	}
}