//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.resolveBuilderMng;
//
//import com.eleven.common.PrintHelper;
//import com.eleven.common.StringHelper;
//import com.eleven.javaAutomationTools.codeCreator.core.ClassScriptBuilder;
//import com.eleven.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//import com.eleven.javaAutomationTools.codeCreator.core.scriptTemplate.TGCMAppProxyTemplate;
//import com.eleven.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class TGCMAppHttpDataBuilder extends BaseBuilder {
//
//	@Override
//	public void buildClassStructureToScript(String jsonFileName, String rootNameSpace, String savePath,
//											IScriptTemplate temp, String jsonFilePath) {
//		String proxyPackageName;
//
//		proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";
//
////		mNameSpace = rootNameSpace + "."+proxyPackageName;
//
//		try {
//
//			JSONObject rootJo;
//			{//file json
//				String jsonStr = readJsonFile(jsonFilePath);
//				rootJo = new JSONObject(jsonStr);
//			}
//
//			//serviceName
//			String serviceName;
//			{
//				serviceName = rootJo.getString("serviceName");
////				mNameSpace+="."+serviceName;
//				String s = StringHelper.lowerCaseFirst(serviceName.replace(".ashx", ""));
//				mNameSpace = rootNameSpace+ "." + s + "."+proxyPackageName;
//				savePath += s+"\\";
////				savePath += StringHelper.lowerCaseFirst(s)+"\\";
//			}
//
//			//action
//			String action;
//			{
//				action = rootJo.getString("action");
//
//			}
//			String preFix = StringHelper.upperCaseFirst(jsonFileName);
//
//			ClassStructureInfo rootReqClass = null;
//			boolean reqIsArray = false;
//			String reqClassName = null;
//			{//req json
//				Object o = rootJo.get("req");
//				if(!o.toString().toLowerCase().equals("null")){
//					JSONObject jo = rootJo.getJSONObject("req");
//					String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"Input";
////					String rootClassName = "Req"+StringHelper.upperCaseFirst(jsonFileName) +"Input";
//					PrintHelper.print(jo.toString());
//					rootReqClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,preFix);
//					PrintHelper.print("rootReqClass:"+rootReqClass.getClassName());
//					reqClassName = rootClassName;
//				}
//			}
//
//			ClassStructureInfo rootResClass = null;
//			boolean resIsArray = false;
//			String resClassName = null;
//			{//res json
////				String jsonStr = rootJo.getString("res");
//				Object o = rootJo.get("res");
//				if(!o.toString().toLowerCase().equals("null")){
//					JSONObject jo = null;// = rootJo.getJSONObject("res");
////					if(o instanceof JSONObject ){
//						jo = rootJo.getJSONObject("res");
////						resIsArray = false;
////					}else if(o instanceof JSONArray ){
////						JSONArray ja = rootJo.getJSONArray("res");
////						jo = ja.getJSONObject(0);
////						resIsArray = true;
////					}
//
//					String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"Output";
////					String rootClassName = "Res"+StringHelper.upperCaseFirst(jsonFileName) +"Output";
//					PrintHelper.print(jo.toString());
//					rootResClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,preFix);
//					PrintHelper.print("rootResClass:"+rootResClass.getClassName() + "  " + rootClassName);
//					resClassName = rootClassName;
//				}
//			}
//
//			{
//				String className = StringHelper.upperCaseFirst(jsonFileName) + "ProxyApi";
////				String className = StringHelper.upperCaseFirst(action) + "ProxyApi";
//				ClassStructureInfo proxyClass = new ClassStructureInfo();
//				proxyClass.setClassName(className);
//				proxyClass.setNameSpace(mNameSpace);
//
//				TGCMAppProxyTemplate proxyTemp =  new TGCMAppProxyTemplate();
//				String scirpt = proxyTemp.getScript("com.wuhanins.core",
//						serviceName,
//						action,
//						proxyClass,
//						reqClassName,
//						resClassName,
//						reqIsArray,
//						resIsArray);
//				ClassScriptBuilder.getInstance().saveProxyScript(proxyTemp,savePath, proxyPackageName,className,
//						scirpt);
//
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
