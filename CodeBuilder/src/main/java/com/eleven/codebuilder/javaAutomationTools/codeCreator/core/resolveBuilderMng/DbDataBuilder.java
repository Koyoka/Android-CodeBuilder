//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.resolveBuilderMng;
//
//
//import com.eleven.codebuilder.common.PrintHelper;
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class DbDataBuilder extends BaseBuilder {
//
//	@Override
//	public void buildClassStructureToScript(String jsonFileName,String rootNameSpace, String savePath, IScriptTemplate temp,
//			String jsonFilePath) {
//
//		String rootClassName;
//		String proxyPackageName;
//
//		proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";
//		rootClassName = jsonFileName +"Body";
//		mNameSpace = rootNameSpace + "."+proxyPackageName;
//
//		//1. ��ȡjson
//		try {
//			String jsonStr = readJsonFile(jsonFilePath);
//			JSONObject rootJo = new JSONObject(jsonStr);
//			PrintHelper.print(jsonStr);
//			buildScriptByJsonString(rootJo,rootClassName,proxyPackageName,savePath,temp,null);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public void buildClassStructureToScript(String jsonFileName, String rootNameSpace, String savePath, IScriptTemplate proxyTemplate, IScriptTemplate dtoTemplate, String jsonFilePath) {
//
//	}
//}
