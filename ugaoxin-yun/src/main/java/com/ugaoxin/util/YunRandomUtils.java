package com.ugaoxin.util;

import java.util.*;
 

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunRandomUtils.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public final class YunRandomUtils {

	/*字符和数字 */
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/*字符 */
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/*数字 */
	public static final String NUMBERCHAR = "0123456789";

	private YunRandomUtils() {
		throw new AssertionError();
	}

	 
	public static int integer(int scopeMin, int scoeMax) {
		Random random = new Random();
		return random.nextInt(scoeMax) % (scoeMax - scopeMin + 1) + scopeMin;
	}

	/** 
	 * 随机
	 */
	public static String number(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}

	 
	public static String getString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}
 
	public static String getMixString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}
 
	public static String getLowerString(int length) {
		return getMixString(length).toLowerCase();
	}

	 
	public static String getUpperString(int length) {
		return getMixString(length).toUpperCase();
	}
 
	public static String getZeroString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	 
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuilder sb = new StringBuilder();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(getZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException();
		}
		sb.append(strNum);
		return sb.toString();
	}

	 
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuilder sb = new StringBuilder();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(getZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException();
		}
		sb.append(strNum);
		return sb.toString();
	}

	 
	public static int getNotSimple(int[] param, int len) {
		Random rand = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
		}
		return result;
	}
 
	public static <T> T randomItem(T[] param) {
		int index = integer(0, param.length);
		return param[index];
	}

	 
	@SuppressWarnings("unused")
	private static String strMultiplication(String str, int multiplication) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < multiplication; i++) {
			buffer.append(str);
		}
		return buffer.toString();
	}

	 
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}
 
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		String temp = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
		return temp.toUpperCase();
	}

}
