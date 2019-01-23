package com.ugaoxin.enums;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/*
 * mybatisplus的特性
 */
public enum  SexEnum implements IEnum  {
	女(0,"女"),
	男(1,"男"),
	保密(-1,"保密");
   
	private Integer value;
	private String desc;
	
	SexEnum(final Integer value,final String desc){
		
		this.value=value;
		this.desc=desc;
	}
	
	
	@Override
	public Serializable getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
	
	@JsonValue
	public String getDesc(){
		
		return this.desc;
	}
	
	
	public static SexEnum valueOfCode(Integer code){
		for(SexEnum value:SexEnum.values()) {
			if(value.getValue()==code) {
				return value;
			}
		}
		throw new RuntimeException("暂无");
	}

}
