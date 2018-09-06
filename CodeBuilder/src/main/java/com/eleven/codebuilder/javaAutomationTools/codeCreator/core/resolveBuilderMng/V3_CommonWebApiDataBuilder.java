//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.resolveBuilderMng;
//
//
//import com.eleven.codebuilder.common.PrintHelper;
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.ClassScriptBuilder;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by eleven on 2016/4/13.
// */
//public class V3_CommonWebApiDataBuilder extends BaseBuilder {
//
//    private String[] mPkgs;
//
//    public V3_CommonWebApiDataBuilder(){
//        mPkgs = new String[]{
//                "com.eleven.jpromiser.core.Promiser",
//                "com.eleven.httpvolleypromiser.proxy.ProviderContext",
//                "com.eleven.httpvolleypromiser.proxy.base.BaseProxy",
//                "com.eleven.httpvolleypromiser.IProxyDataProvider",
//                "com.eleven.httpvolleypromiser.WebApiVolleyHelper",
//                "com.eleven.httpvolleypromiser.proxy.event.IHttpHelperListener",
//                "com.eleven.httpvolleypromiser.proxy.event.IOnProxyDoneForStringListener",
//                "com.eleven.httpvolleypromiser.proxy.event.IOnProxyDoneListener"
//
//        };
//    }
//    public V3_CommonWebApiDataBuilder(String[] pkgs){
//        mPkgs = pkgs;
//    }
//    @Override
//    public void buildClassStructureToScript(String jsonFileName, String rootNameSpace, String savePath,
//                                            IScriptTemplate proxyTemplate,
//                                            IScriptTemplate dtoTemplate, String jsonFilePath) {
//        String proxyPackageName;
//        proxyPackageName = StringHelper.lowerCaseFirst(jsonFileName) + "Proxy";
//
//        JSONObject rootJo;
//        {//file json
//            String jsonStr = readJsonFile(jsonFilePath);
//            rootJo = new JSONObject(jsonStr);
//        }
//
//        //apiPath
//        String apiUrl = "";
//        {
//            apiUrl = rootJo.getString("apiPath").trim();
//        }
//
//        //method
//        String method = "";
//        {
//            method = rootJo.getString("method").toUpperCase();
//        }
//
//        //controller
//        String controller = "";
//        {
//            controller = rootJo.getString("controller");
//            String s =  StringHelper.lowerCaseFirst(controller);
//            mNameSpace = rootNameSpace+ "." + s + "."+proxyPackageName; ;
//            savePath += s+"\\";
//        }
//
//        //getReq
//        ClassStructureInfo getReqCls = null;
//        String getReqClsName = null;
//        {
//            getReqCls = new ClassStructureInfo();
//            if(buildGetReqCls(getReqCls,apiUrl)){
//                getReqClsName = StringHelper.upperCaseFirst(jsonFileName) +"GInput";
//                getReqCls.setClassName(getReqClsName);
//                getReqCls.setNameSpace(mNameSpace);
//                PrintHelper.print("getReqCls:"+getReqCls.getClassName());
//                V1_CommonGetInputDtoTemplate getInputTemp = new V1_CommonGetInputDtoTemplate();
//                String script = getInputTemp.getScript(getReqCls);
//                ClassScriptBuilder.getInstance().saveProxyScript(getInputTemp,savePath, proxyPackageName,getReqClsName,
//                        script);
//            }
//        }
//
//        //postReq
//        String preFix = StringHelper.upperCaseFirst(jsonFileName);
//        ClassStructureInfo rootReqClass = null;
////        ClassStructureInfo postReqCls = null;
//        String reqClassName = null;
//        {
//            Object o = rootJo.get("req");
//            if(!o.toString().toLowerCase().equals("null")){
//                JSONObject jo = rootJo.getJSONObject("req");
//                String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"PInput";
//                PrintHelper.print(jo.toString());
//                rootReqClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,dtoTemplate,preFix);
////                rootReqClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,preFix+"Req");
//                PrintHelper.print("rootReqClass:"+rootReqClass.getClassName());
//                reqClassName = rootClassName;
//            }
//        }
//
//        //res
//        ClassStructureInfo rootResClass = null;
//        String resClassName = null;
//        {
//            Object o = rootJo.get("res");
//            if(!o.toString().toLowerCase().equals("null")){
//                JSONObject jo = null;
//                jo = rootJo.getJSONObject("res");
//
//                String rootClassName = StringHelper.upperCaseFirst(jsonFileName) +"Output";
//                PrintHelper.print(jo.toString());
//                rootResClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,dtoTemplate,preFix);
////                rootResClass = buildScriptByJsonString(jo,rootClassName,proxyPackageName,savePath,temp,preFix+"Res");
//                PrintHelper.print("rootResClass:"+rootResClass.getClassName() + "  " + rootClassName);
//                resClassName = rootClassName;
//            }
//        }
//
//        {
//
//            String className = StringHelper.upperCaseFirst(jsonFileName) + "ProxyApi";
//            ClassStructureInfo proxyClass = new ClassStructureInfo();
//            proxyClass.setClassName(className);
//            proxyClass.setNameSpace(mNameSpace);
//
////            V3_CommonWebApiProxyTemplate proxyTemp1 = new V3_CommonWebApiProxyTemplate();
//            IScriptTemplate proxyTemp =  proxyTemplate;
//            String scirpt = proxyTemp.getProxyScript(//"com.wuhanins.core",
//                    mPkgs,
//                    apiUrl,
//                    method,
//                    proxyClass,
//                    getReqClsName,
//                    reqClassName,
//                    resClassName
//                    );
//            ClassScriptBuilder.getInstance().saveProxyScript(proxyTemp,savePath, proxyPackageName,className,
//                    scirpt);
//        }
//    }
//
//    private boolean buildGetReqCls(ClassStructureInfo cls, String apiUrl){
//        String regEx = "\\{[\\s\\S]*?\\}";
//        Pattern pat = Pattern.compile(regEx);
//        Matcher mat =
//                pat.matcher(apiUrl);
//
//        ArrayList<ClassFieldInfo> clsFieldInfos = new ArrayList<>();
//        boolean hasGInput = false;
//        while(mat.find()){
//            String s = mat.group().replace("{","").replace("}","");
//
//            ClassFieldInfo field = new ClassFieldInfo();
//            field.setClassName(cls.getClassName());
//            field.setFieldType("String");
//            field.setFieldName(s);
//            clsFieldInfos.add(field);
//            hasGInput = true;
//        }
//        cls.setFieldList(clsFieldInfos);
//
//        return hasGInput;
//    }
//}
