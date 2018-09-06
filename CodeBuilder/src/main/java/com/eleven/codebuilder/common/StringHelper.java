package com.eleven.codebuilder.common;

public class StringHelper {
	 public static String upperCaseFirst(String value) {

		char[] array = value.toCharArray();
		array[0] = Character.toUpperCase(array[0]);
		return new String(array);
    }
	 
	 public static String lowerCaseFirst(String value){
		 char[] array = value.toCharArray();
			array[0] = Character.toLowerCase(array[0]);
			return new String(array);
	 }

}
