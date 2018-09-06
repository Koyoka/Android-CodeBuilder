package com.eleven.codebuilder.common;

import com.google.gson.Gson;

public class PrintHelper {
	
	
	public static void print(String msg){
		System.out.println(msg);
	}
	
	public static void print(Object obj){
		String jsonStr = new Gson().toJson(obj);	
		print(jsonStr);
	}
	
	public static void printErr(String msg){
		System.err.println(msg);
	}
	
	

}
