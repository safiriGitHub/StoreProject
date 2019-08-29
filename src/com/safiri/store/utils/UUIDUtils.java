package com.safiri.store.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	public static String getUUID64(){
		return getUUID()+getUUID();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
