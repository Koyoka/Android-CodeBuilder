package com.eleven.codebuilder.javaAutomationTools.codeCreator.core;


import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ClassScriptBuilder {
	
	public static ClassScriptBuilder getInstance(){
		return new ClassScriptBuilder();
	}
	IScriptTemplate mTemp;
	private String mProxyPackageName;
	public void saveScript(ArrayList<ClassStructureInfo> classStructList, IScriptTemplate temp, String savePath, String proxyPackageName){
		mTemp = temp;
		mProxyPackageName = proxyPackageName;
		
		for(ClassStructureInfo clsStruct : classStructList){
			
			String script = 
					temp.getScript(clsStruct);
//			PrintHelper.print(script);
			saveToDir(savePath,clsStruct.getClassName(),script);
		}
	}
	
	public void saveProxyScript(IScriptTemplate temp,
			String savePath,
			String proxyPackageName,
			String className,
			String script){
		mTemp = temp;
		mProxyPackageName = proxyPackageName;
	
//		String script = temp.getScript(classs);
		saveToDir(savePath,className,script);
	}
	
	private void saveToDir(String savePath,String fileName,String script){
		savePath += mProxyPackageName+"\\";
		File dir = new File(savePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		
		File fp=new File(savePath + StringHelper.upperCaseFirst(fileName) + "." + mTemp.getFileExName());
		PrintWriter pfp;	
		try {
			pfp = new PrintWriter(fp);
			pfp.print(script);
			pfp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
