//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.resolveBuilderMng;
//
//
//import com.eleven.codebuilder.common.PrintHelper;
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.ClassFieldBuilder;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.ClassScriptBuilder;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Scanner;
//
//
//public abstract class BaseBuilder {
//
//	protected String mFileName;
//	protected String mNameSpace;
//	protected ArrayList<ClassFieldInfo> mJsonFieldInfoList;
//
//	public void build(String rootNameSpace, String savePath,IScriptTemplate proxyTemplate, IScriptTemplate dtoTemplate, String... jsonFilePaths){
//
//		for(String jsonFilePath : jsonFilePaths){
//			if(jsonFilePath.trim().equals("")){
//				continue;
//			}
//
//			String jsonFileName;
//			if((jsonFileName = initAndGetRootClassName(jsonFilePath)) == null){
//				PrintHelper.printErr("no file,create stop!");
//				return;
//			}
//			buildClassStructureToScript(jsonFileName,rootNameSpace,savePath,proxyTemplate,dtoTemplate,jsonFilePath);
//		}
//	}
//
//	protected String initAndGetRootClassName(String jsonFilePath){
////		mJsonFieldInfoList = new ArrayList<>();
//
//		File jsonFile = new File(jsonFilePath);
//		if(!jsonFile.exists()){
//			PrintHelper.printErr("no file in:"+jsonFilePath);
//			return null;
//		}
//
//		mFileName = jsonFile.getName().replace(".json", "");
//		PrintHelper.printErr("-----------------");
//		PrintHelper.printErr(mFileName);
//		PrintHelper.printErr("-----------------");
//		return mFileName;
//	}
//
//	public abstract void buildClassStructureToScript(String jsonFileName,
//													 String rootNameSpace,
//													 String savePath,
//													 IScriptTemplate proxyTemplate,
//													 IScriptTemplate dtoTemplate,
//													 String jsonFilePath);
//
//
//	protected ClassStructureInfo buildScriptByJsonString(//String jsonStr,
//														 JSONObject rootJo,
//														 String rootClassName, String proxyPackageName,
//														 String savePath, IScriptTemplate temp, String preFix){
//		mJsonFieldInfoList = new ArrayList<>();
//		//1. json
//		resolveJson2ClassField(rootClassName,rootJo,preFix);
//
//		//2. �����Ӧ����ӿ��б�
//		ArrayList<ClassStructureInfo> classStructList = buildField2ClassStructure();
//
//		//3. save
//		ClassScriptBuilder.getInstance().saveScript(classStructList, temp, savePath,proxyPackageName);
//
//		return classStructList.get(0);
//	}
//
//	private void resolveJson2ClassField(String className,JSONObject rootJo,String preFix){
//
//		try {
////			JSONObject rootJo = new JSONObject(jsonStr);
//			resolveJson2ClassFieldByJsonObject(className,rootJo,preFix);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	protected void resolveJson2ClassFieldByJsonObject(String className,JSONObject jo,String preFix) throws JSONException{
//
//		 for (Iterator<String> iter = jo.keys(); iter.hasNext();) {
//			 String key = iter.next();
//
//			 Object val = jo.get(key);
//
//			 ClassFieldInfo fieldInfo = null;
//			 if(val instanceof JSONObject){
//				PrintHelper.print(val+" ..... JSONObject " + key);
//
//				String classFieldName = StringHelper.upperCaseFirst(key);
//				String newClassName = "";//classFieldName + "Item";
//				if(preFix == null)
//					newClassName = classFieldName+"Item";
//				else
//					newClassName = preFix + classFieldName;
//
//				resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject) val,preFix);
//
//				fieldInfo = new ClassFieldInfo();
//				fieldInfo.setClassName(className);
//				fieldInfo.setFieldName(key);
////				fieldInfo.setFieldName(classFieldName);
//				fieldInfo.setFieldType(newClassName);
//
//			 }else if(val instanceof JSONArray){
//				PrintHelper.print(val+" ..... JSONArray " + key);
//
//				String classFieldName = StringHelper.upperCaseFirst(key);
//				String newClassName = "";
//				if(preFix == null)
//					newClassName = classFieldName+"Item";
//				else
//					newClassName = preFix + classFieldName;
//
//				JSONArray ja = (JSONArray)val;
//				if(ja.length() == 0){
//					continue;
//				}
//
//				if(ja.get(0) instanceof JSONObject){
//					resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject)ja.get(0),preFix);
//
//					fieldInfo = new ClassFieldInfo();
//					fieldInfo.setClassName(className);
//					fieldInfo.setFieldName(key);
////					fieldInfo.setFieldName(classFieldName);
//					fieldInfo.setFieldType(newClassName);
//					fieldInfo.setArray(true);
//				}else{
//					fieldInfo = ClassFieldBuilder.get(ja.get(0),className,key);
//					fieldInfo.setArray(true);
//				}
//
//			 }else{
//				fieldInfo = ClassFieldBuilder.get(val,className,key);
//			 }
//			 if(fieldInfo != null)
//				 mJsonFieldInfoList.add(fieldInfo);
//		 }
//	}
//
//	protected ArrayList<ClassStructureInfo> buildField2ClassStructure(){
//
//		Map<String,ClassStructureInfo> map = new HashMap<String, ClassStructureInfo>();
//
//		for(ClassFieldInfo item : mJsonFieldInfoList){
//			ClassStructureInfo classStruct;
//
//			String mapKey = item.getClassName();
//			if(!map.containsKey(mapKey)){
//				classStruct = new ClassStructureInfo();
//				classStruct.setFieldList(new ArrayList<ClassFieldInfo>());
//				map.put(mapKey, classStruct);
//			}else{
//				classStruct = map.get(mapKey);
//			}
//
//			classStruct.setClassName(mapKey);
//			classStruct.getFieldList().add(item);
//			classStruct.setNameSpace(mNameSpace);
//		}
//
//		ArrayList<ClassStructureInfo> classStructInfoList = new ArrayList<>();
//		for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext();){
//			String className = iter.next();
//			ClassStructureInfo classStruct = map.get(className);
//			classStructInfoList.add(classStruct);
//		}
//
//		return classStructInfoList;
//	}
//
//	protected String readJsonFile(String jsonFilePath){
//		File f = new File(jsonFilePath);
//
//		if(!f.exists())
//			return null;
//
//		StringBuilder sb = new StringBuilder();
//		 try {
//            Scanner in = new Scanner(f);
//            while (in.hasNextLine()) {
//                String str = in.nextLine();
//                sb.append(str+"\r\n");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//		return sb.toString();
//	}
//
//
//}
