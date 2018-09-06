package com.eleven.codebuilder.javaAutomationTools.codeCreator.core;


import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class JsonResolveHelper {
	
	public static JsonResolveHelper getInstance(){
		
		return new JsonResolveHelper();
	}
	
	private String mFileName;
	private String mNameSpace;
	private ArrayList<ClassFieldInfo> mJsonFieldInfoList;

	public void build(String rootNameSpace, String savePath, IScriptTemplate temp, String... jsonFilePaths){
		
		for(String jsonFilePath : jsonFilePaths){
			if(jsonFilePath.trim().equals("")){
				continue;
			}
			buildClassStructureToScript(rootNameSpace,savePath,temp,jsonFilePath);
		}
	}
	
	public void buildByApiDoc(String rootNameSpace,String savePath,IScriptTemplate temp,String... jsonFilePaths){
		
		for(String jsonFilePath : jsonFilePaths){
			if(jsonFilePath.trim().equals("")){
				continue;
			}
			buildApiDocClassStructureToScript(rootNameSpace,savePath,temp,jsonFilePath);
		}
	}
	
	public void buildClassStructureToScript(String rootNameSpace, String savePath, IScriptTemplate temp, String jsonFilePath){
		
//		mNameSpace = rootNameSpace;
		String rootClassName;
		String jsonFileName;
		String proxyPackageName;
		
		if((jsonFileName = initAndGetRootClassName(jsonFilePath)) == null){
			PrintHelper.printErr("no file,create stop!");
			return;
		}
		proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";
		rootClassName = jsonFileName +"Body";
		mNameSpace = rootNameSpace + "."+proxyPackageName; 
		
		//1. ��ȡjson
		try {
			String jsonStr = readJsonFile(jsonFilePath);
			JSONObject rootJo = new JSONObject(jsonStr);
			PrintHelper.print(jsonStr);
			buildScriptByJsonString(rootJo,rootClassName,proxyPackageName,savePath,temp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buildApiDocClassStructureToScript(String rootNameSpace,String savePath,IScriptTemplate temp,String jsonFilePath){

		String jsonFileName;
		String proxyPackageName;

		if((jsonFileName = initAndGetRootClassName(jsonFilePath)) == null){
			PrintHelper.printErr("no file,create stop!");
			return;
		}
		proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";

		mNameSpace = rootNameSpace + "."+proxyPackageName;

		try {

			JSONObject rootJo;
			{//file json
				String jsonStr = readJsonFile(jsonFilePath);
				rootJo = new JSONObject(jsonStr);
			}

			{//req json

				JSONObject jo = rootJo.getJSONObject("req");
				String rootClassName = "Req"+StringHelper.upperCaseFirst(jsonFileName) +"Input";
				PrintHelper.print(jo.toString());
				buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp);
			}

			{//res json
//				String jsonStr = rootJo.getString("res");
				JSONObject jo = rootJo.getJSONObject("res");
				String rootClassName = "Res"+StringHelper.upperCaseFirst(jsonFileName) +"output";
				PrintHelper.print(jo.toString());
				buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String initAndGetRootClassName(String jsonFilePath){
		mJsonFieldInfoList = new ArrayList<>();

		File jsonFile = new File(jsonFilePath);
		if(!jsonFile.exists()){
			PrintHelper.printErr("no file in:"+jsonFilePath);
			return null;
		}

		mFileName = jsonFile.getName().replace(".json", "");
		PrintHelper.printErr("-----------------");
		PrintHelper.printErr(mFileName);
		PrintHelper.printErr("-----------------");
		return mFileName;
	}

	public void buildScriptByJsonString(//String jsonStr,
			JSONObject rootJo,
			String rootClassName,String proxyPackageName,
			String savePath,IScriptTemplate temp){

		//1. ������ȡ����json�ֶ�
		resolveJson2ClassField(rootClassName,rootJo);

		//2. �����Ӧ����ӿ��б�
		ArrayList<ClassStructureInfo> classStructList = buildField2ClassStructure();
		
		//3. save
		ClassScriptBuilder.getInstance().saveScript(classStructList, temp, savePath,proxyPackageName);
		
	}
	
	private void resolveJson2ClassField(String className,JSONObject rootJo){
		
		try {
//			JSONObject rootJo = new JSONObject(jsonStr);
			resolveJson2ClassFieldByJsonObject(className,rootJo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resolveJson2ClassFieldByJsonObject(String className,JSONObject jo) throws JSONException{

		 for (Iterator<String> iter = jo.keys(); iter.hasNext();) {
			 String key = iter.next();
			
			 Object val = jo.get(key);
			
			 ClassFieldInfo fieldInfo = null;
			 if(val instanceof JSONObject){
				PrintHelper.print(val+" ..... JSONObject " + key);
				
				String classFieldName = StringHelper.upperCaseFirst(key);
				String newClassName = classFieldName + "Item";
				
				resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject) val);
				
				fieldInfo = new ClassFieldInfo();
				fieldInfo.setClassName(className);
				fieldInfo.setFieldName(classFieldName);
				fieldInfo.setFieldType(newClassName);
				
			 }else if(val instanceof JSONArray){
				PrintHelper.print(val+" ..... JSONArray " + key);
				
				String classFieldName = StringHelper.upperCaseFirst(key);
				String newClassName = classFieldName+"Item";
				
				JSONArray ja = (JSONArray)val;
				if(ja.length() == 0){
					continue;
				}
				
				if(ja.get(0) instanceof JSONObject){
					resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject)ja.get(0));
					
					fieldInfo = new ClassFieldInfo();
					fieldInfo.setClassName(className);
					fieldInfo.setFieldName(classFieldName);
					fieldInfo.setFieldType(newClassName);
					fieldInfo.setArray(true);
				}else{
					fieldInfo = ClassFieldBuilder.get(ja.get(0),className,key);
					fieldInfo.setArray(true);
				}
				
			 }else{
				fieldInfo = ClassFieldBuilder.get(val,className,key);
			 }
			 if(fieldInfo != null)
				 mJsonFieldInfoList.add(fieldInfo);
		 }
	}
	
	private ArrayList<ClassStructureInfo> buildField2ClassStructure(){
		
		Map<String,ClassStructureInfo> map = new HashMap<String, ClassStructureInfo>();
		
		for(ClassFieldInfo item : mJsonFieldInfoList){
			ClassStructureInfo classStruct;
			
			String mapKey = item.getClassName();
			if(!map.containsKey(mapKey)){
				classStruct = new ClassStructureInfo();
				classStruct.setFieldList(new ArrayList<ClassFieldInfo>());
				map.put(mapKey, classStruct);
			}else{
				classStruct = map.get(mapKey);
			}
			
			classStruct.setClassName(mapKey);
			classStruct.getFieldList().add(item);
			classStruct.setNameSpace(mNameSpace);
		}
		
		ArrayList<ClassStructureInfo> classStructInfoList = new ArrayList<>();
		for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext();){
			String className = iter.next();
			ClassStructureInfo classStruct = map.get(className);
			classStructInfoList.add(classStruct);
		}
		
		return classStructInfoList;
	}
	
	private String readJsonFile(String jsonFilePath){
		File f = new File(jsonFilePath);
		
		if(!f.exists())
			return null;
		
		StringBuilder sb = new StringBuilder();
		 try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String str = in.nextLine();
                sb.append(str+"\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
		return sb.toString();
	}
	
	public void test(){
		
//		String path = "C:\\ElevenWorkSpace\\dev\\java\\workspace\\com.eleven.JavaAutomationTools\\src\\com\\eleven\\JavaAutomationTools\\codeCreator\\jsonfile\\";
//		String filePath = path + "test.json";
//		
//		buildClassStructureToScript(filePath);
		
//		PrintHelper.print(mJsonFieldInfoList);
//		String jsonStr;
//		if((jsonStr = readJsonFile(filePath)) == null){
//			PrintHelper.printErr("no file");
//			return;
//		}
//		PrintHelper.print(jsonStr);
		
	}

}





















