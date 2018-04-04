package com.students.utils;

import java.util.Random;

/**
 * 用于处理字符串转换为其他数据类型
 * @author kww
 * 2016年7月29日
 */
public class StringUtils {

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 获取任意位的随机字符串(0-9 a-z A-Z)
	 * 
	 * @param size
	 *            位数
	 * @return
	 */
	public static final String getRandomNum(int size) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	// 如果传入为 null 则返回空串
	public static final String getString(Object obj){
		String result="";
		if(obj!=null){
			result=String.valueOf(obj);
		}
		return result;
	}
	
	// 返回 Integer 对象
	public static final Integer getInteger(Object obj){
		Integer result=null;
		if(obj!=null && !obj.equals("")){
			result=Integer.valueOf((String.valueOf(obj)));
		}
		return result;
	}
	
	// int 不能为null，默认返回0
	public static final int getInt(Object obj){
		int result=0;
		if(obj!=null && !obj.equals("")){
			result=Integer.valueOf(String.valueOf(obj));
		}
		return result;
	}
	
	// 转换为float型小数
	public static final float getFloat(Object obj){
		float result=0;
		if(obj!=null && !obj.equals("")){
			result=Float.valueOf((String.valueOf(obj)));
		}
		return result;
	}
	
	// 转换为double型小数
	public static final double getDouble(Object obj){
		double result=0;
		if(obj!=null && !obj.equals("")){
			result=Double.valueOf(String.valueOf(obj));
		}
		return result;
	}
	
	// 字符串转换为大写
	public static final String toLowerCase(Object obj){
		String result=getString(obj);
		result=result.toLowerCase();
		return result;
	}
	
	// 字符串转换为小写
	public static final String toUpperCase(Object obj){
		String result=getString(obj);
		result=result.toUpperCase();
		return result;
	}
	
	// 传入字符串为null 或者空串 或者全部是空格 就返回true
	public static final boolean isBlank(Object obj){
		boolean result=false;
		result=getString(obj).trim().isEmpty();
		return result;
	}
	
	/**
	 * 传入字符串数字返回合并后的字符串，如果传入为null则返回空串
	 * @param array 字符串数组
	 * @param separator 分隔符
	 * @return
	 */
	public static final String jion(String[] array, String separator){
		String result="";
		if(array!=null){
			for (int i = 0; i < array.length; i++) {
				if(i==0){
					result=array[i];
				}else{
					result=result+separator+array[i];
				}
			}
		}
		return result;
	}
}
