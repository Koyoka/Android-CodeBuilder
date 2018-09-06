package com.eleven.codebuilder.javaAutomationTools.codeCreator.core;

import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.BuildHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.VelocityEngineHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ProxyStructureInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebApiDataBuilder {
    public static WebApiDataBuilder newInstance(){
        return new WebApiDataBuilder();
    }

    public void build(String rootNameSpace,
                      String saveDirPath, String jsonFileDir){
        build(
                rootNameSpace,
                "/assets/ProxyTemp.vm",
                "/assets/GetInputDtoTemp.vm",
                "/assets/ObjectDtoTemp.vm",
                saveDirPath,
                jsonFileDir
        );
    }
    public void build(String rootNameSpace,
                      String proxyTemplateFilePath, String dtoTemplateFilePath, String objectTemplate,
                      String saveDirPath, String jsonFileDir){

        File dir = new File(jsonFileDir);
        ArrayList<String> jsonFileList = new ArrayList<>();
        buildFileList(dir, jsonFileList);
        if(jsonFileList.size() != 0){
            String[] array = new String[jsonFileList.size()];
            array = jsonFileList.toArray(array);
            build( rootNameSpace,
                     proxyTemplateFilePath,  dtoTemplateFilePath,  objectTemplate,
                     saveDirPath, array);
        }

    }
    private void buildFileList(File dir, ArrayList<String> jsonFileList){
        if(dir.exists() && dir.isDirectory()){

            for (File childFile :
                    dir.listFiles()) {
                if(childFile.isFile() && childFile.getName().endsWith(".json")){
                    jsonFileList.add(childFile.getAbsolutePath());
                }else if(childFile.isDirectory()){
                    buildFileList(childFile, jsonFileList);
                }
            }
        }
    }


    public void build(String rootNameSpace,
                      String proxyTemplateFilePath, String dtoTemplateFilePath, String objectTemplate,
                      String saveDirPath, String... jsonFilePaths){

        for(String jsonFilePath : jsonFilePaths){
            if(jsonFilePath.trim().equals("")){
                continue;
            }

            String jsonFileName;
            if((jsonFileName = BuildHelper.getClassNameByFileName(jsonFilePath)) == null){

                PrintHelper.printErr("no file,create stop!");
                return;
            }

            createClassToScript(
                    rootNameSpace,
                    jsonFileName,
                    saveDirPath,
                    proxyTemplateFilePath,dtoTemplateFilePath,objectTemplate,
                    jsonFilePath
            );
        }
    }



    private void createClassToScript(String rootNameSpace,
                                     String jsonFileName,
                                     String savePath,
                                     String proxyTemplate,
                                     String getInputDtoTemplate,
                                     String objectDtoTemplate,
                                     String jsonFilePath){

        String mNameSpace;
        String proxyPackageName;

        proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";

        JSONObject rootJo;
        {//file json
            String jsonStr = BuildHelper.readJsonFile(jsonFilePath);
            rootJo = new JSONObject(jsonStr);
        }

        //apiPath
        String apiUrl = "";
        {
            apiUrl = rootJo.getString("apiPath").trim();
        }

        //method
        String method = "";
        {
            method = rootJo.getString("method").toUpperCase();
        }

        //controller
        String controller = "";
        {
            controller = rootJo.getString("controller");
            String s =  StringHelper.lowerCaseFirst(controller);
            mNameSpace = rootNameSpace+ "." + s + "."+proxyPackageName;
            savePath += s+"\\";
        }

        //region #get request dto
        ClassStructureInfo getReqCls = null;
        String getReqClsName = null;
        {
            getReqCls = new ClassStructureInfo();
            if(BuildHelper.buildGetReqCls(getReqCls,apiUrl)){
                getReqClsName = StringHelper.upperCaseFirst(jsonFileName) +"GInput";
                getReqCls.setClassName(getReqClsName);
                getReqCls.setNameSpace(mNameSpace);
                PrintHelper.print("getReqCls:"+getReqCls.getClassName());

                String script = VelocityEngineHelper.createDtoScript(getInputDtoTemplate, getReqCls);

                PrintHelper.print("--------------");
                PrintHelper.print(script);
                PrintHelper.print("--------------");
                BuildHelper.saveToDir(
                        proxyPackageName,
                        savePath,
                        getReqClsName,
                        script
                );
            }
        }
        //endregion

        //region #post Request
        String preFix = StringHelper.upperCaseFirst(jsonFileName);
        ClassStructureInfo rootReqClass = null;
        String reqClassName = null;
        {
            Object o = rootJo.get("req");
            if(!o.toString().toLowerCase().equals("null")){
                JSONObject jo = rootJo.getJSONObject("req");
                String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"PInput";
                PrintHelper.print(jo.toString());
                rootReqClass =
                        DtoJsonFileResolver.newInstance()
                                .buildScriptByJsonString(
                                        mNameSpace,
                                        jo,rootClassName,proxyPackageName,savePath,objectDtoTemplate,preFix);
                PrintHelper.print("rootReqClass:"+rootReqClass.getClassName());
                reqClassName = rootClassName;
            }
        }
        //endregion

        //region #response
        ClassStructureInfo rootResClass = null;
        String resClassName = null;
        {
            Object o = rootJo.get("res");
            if(!o.toString().toLowerCase().equals("null")){
                JSONObject jo = null;
                jo = rootJo.getJSONObject("res");

                String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"Output";
                PrintHelper.print(jo.toString());
                rootResClass =
                        DtoJsonFileResolver.newInstance()
                                .buildScriptByJsonString(
                                        mNameSpace,
                                        jo,rootClassName,proxyPackageName,savePath,objectDtoTemplate,preFix);
                PrintHelper.print("rootResClass:"+rootResClass.getClassName() + "  " + rootClassName);
                resClassName = rootClassName;
            }
        }
        //endregion



        {

            String className = StringHelper.upperCaseFirst(jsonFileName) + "ProxyApi";
            ProxyStructureInfo proxyInfo = new ProxyStructureInfo();
            proxyInfo.setClassName(className);
            proxyInfo.setNameSpace(mNameSpace);
            proxyInfo.setApiUrl(apiUrl);
            proxyInfo.setMethod(method);
            proxyInfo.setGetReqClassName(getReqClsName);
            proxyInfo.setPostReqClassName(reqClassName);
            proxyInfo.setResClassName(resClassName);

            String script = VelocityEngineHelper.creteProxyScript(proxyTemplate, proxyInfo);
            PrintHelper.print("proxy class --------------");
            PrintHelper.print(script);
            BuildHelper.saveToDir(
                    proxyPackageName,
                    savePath,
                    className,
                    script
            );
//            ClassStructureInfo proxyClass = new ClassStructureInfo();
//            proxyClass.setClassName(className);
//            proxyClass.setNameSpace(mNameSpace);

            //region create code script
//            IScriptTemplate proxyTemp =  proxyTemplate;
//            String scirpt = proxyTemp.getProxyScript(//"com.wuhanins.core",
//                    mPkgs,
//                    apiUrl,
//                    method,
//                    proxyClass,
//                    getReqClsName,
//                    reqClassName,
//                    resClassName
//            );
            //endregion

//            ClassScriptBuilder.getInstance().saveProxyScript(proxyTemp,savePath, proxyPackageName,className,
//                    scirpt);
        }

    }

    //region #DtoJsonFileResolver

    static class DtoJsonFileResolver {

        public static DtoJsonFileResolver newInstance(){
            return new DtoJsonFileResolver();
        }

        private ArrayList<ClassFieldInfo> mJsonFieldInfoList;
        private String mNameSpace;
        public ClassStructureInfo buildScriptByJsonString(//String jsonStr,
                                                            String nameSpace,
                                                             JSONObject rootJo,
                                                             String rootClassName, String proxyPackageName,
                                                             String savePath, String dtoTempFilePath, String preFix){
            mJsonFieldInfoList = new ArrayList<>();
            mNameSpace = nameSpace;
            //1. json
            resolveJson2ClassField(rootClassName,rootJo,preFix);

            //2. �����Ӧ����ӿ��б�
            ArrayList<ClassStructureInfo> classStructList
                    = buildField2ClassStructure();

            //3. save
            for(ClassStructureInfo clsStruct : classStructList){
                String script = VelocityEngineHelper.createDtoScript(dtoTempFilePath, clsStruct);
                //TODO: 模板引擎创建脚本
                BuildHelper.saveToDir(
                        proxyPackageName,
                        savePath,
                        clsStruct.getClassName(),
                        script
                );
            }
//            ClassScriptBuilder.getInstance().saveScript(classStructList, temp, savePath,proxyPackageName);

            return classStructList.get(0);
        }

        private void resolveJson2ClassField(String className,JSONObject rootJo,String preFix){

            try {
                resolveJson2ClassFieldByJsonObject(className,rootJo,preFix);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void resolveJson2ClassFieldByJsonObject(String className,JSONObject jo,String preFix) throws JSONException{

            for (Iterator<String> iter = jo.keys(); iter.hasNext();) {
                String key = iter.next();

                Object val = jo.get(key);

                ClassFieldInfo fieldInfo = null;
                if(val instanceof JSONObject){
                    PrintHelper.print(val+" ..... JSONObject " + key);

                    String classFieldName = StringHelper.upperCaseFirst(key);
                    String newClassName = "";//classFieldName + "Item";
                    if(preFix == null)
                        newClassName = classFieldName+"Item";
                    else
                        newClassName = preFix + classFieldName;

                    resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject) val,preFix);

                    fieldInfo = new ClassFieldInfo();
                    fieldInfo.setClassName(className);
                    fieldInfo.setFieldName(key);
//				fieldInfo.setFieldName(classFieldName);
                    fieldInfo.setFieldType(newClassName);

                }else if(val instanceof JSONArray){
                    PrintHelper.print(val+" ..... JSONArray " + key);

                    String classFieldName = StringHelper.upperCaseFirst(key);
                    String newClassName = "";
                    if(preFix == null)
                        newClassName = classFieldName+"Item";
                    else
                        newClassName = preFix + classFieldName;

                    JSONArray ja = (JSONArray)val;
                    if(ja.length() == 0){
                        continue;
                    }

                    if(ja.get(0) instanceof JSONObject){
                        resolveJson2ClassFieldByJsonObject(newClassName,(JSONObject)ja.get(0),preFix);

                        fieldInfo = new ClassFieldInfo();
                        fieldInfo.setClassName(className);
                        fieldInfo.setFieldName(key);
//					fieldInfo.setFieldName(classFieldName);
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
            for (String className : map.keySet()) {
                ClassStructureInfo classStruct = map.get(className);
                classStructInfoList.add(classStruct);
            }

            return classStructInfoList;
        }
    }

    //endregion


}
