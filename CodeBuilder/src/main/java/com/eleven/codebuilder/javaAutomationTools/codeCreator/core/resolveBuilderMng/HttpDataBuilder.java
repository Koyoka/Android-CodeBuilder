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
//public class HttpDataBuilder extends BaseBuilder {
//
//	@Override
//	public void buildClassStructureToScript(String jsonFileName, String rootNameSpace, String savePath,
//											IScriptTemplate temp, String jsonFilePath) {
//
//		String proxyPackageName;
//
//		proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";
//
//		mNameSpace = rootNameSpace + "."+proxyPackageName;
//
//		try {
//
//			JSONObject rootJo;
//			{//file json
//				String jsonStr = readJsonFile(jsonFilePath);
//				rootJo = new JSONObject(jsonStr);
//			}
//
//			{//req json
//
//				JSONObject jo = rootJo.getJSONObject("req");
//				String rootClassName = "Req"+StringHelper.upperCaseFirst(jsonFileName) +"Input";
//				PrintHelper.print(jo.toString());
//				buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,null);
//			}
//
//			{//res json
////				String jsonStr = rootJo.getString("res");
//				JSONObject jo = rootJo.getJSONObject("res");
//				String rootClassName = "Res"+StringHelper.upperCaseFirst(jsonFileName) +"output";
//				PrintHelper.print(jo.toString());
//				buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,null);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
