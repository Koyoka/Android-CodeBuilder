//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.proxy;
//
//
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
///**
// * Created by eleven on 2016/4/13.
// */
//public class V3_CommonWebApiProxyTemplate extends IScriptTemplate {
//    @Override
//    public String getScript(ClassStructureInfo... clsStructs) {
//        return null;
//    }
//
//    @Override
//    public String getProxyScript(//String helperRootNS,
//                                 String[] importPkgs,
//                                 String apiUrl,
//                                 String method,
//                                 ClassStructureInfo proxyClass,
//                                 String gReqClassName,
//                                 String pReqClassName,
//                                 String resClassName){
//        StringBuilder sb = new StringBuilder();
//
//        "package " + proxyClass.getNameSpace() + ";");
//        line(sb);
//        "import java.util.ArrayList;");
//        line(sb);
//        for (String s :
//                importPkgs) {
//            "import " + s + ";");
//        }
////"import "+helperRootNS+".common.BaseProxy;");
////"import "+helperRootNS+".helper.IHttpHelperListener;");
////"import "+helperRootNS+".helper.IOnProxyDoneListener;");
////"import "+helperRootNS+".helper.IProxyDataProvider;");
////"import "+helperRootNS+".helper.ProviderContext;");
////"import "+helperRootNS+".promise.Promiser;");
//
//        line(sb);
//        String clsName = StringHelper.upperCaseFirst(proxyClass.getClassName());
//        "public class " + StringHelper.upperCaseFirst(proxyClass.getClassName()) + " extends BaseProxy {");
//        {
//            line(sb);
//            "	private String apiUrl = \""+apiUrl+"\";");
//            "	private String method = \""+method+"\";");
////    line(sb);
//            String defineResClsName = resClassName;
////if(resClassName != null){
////    defineResClsName = resClassName;
////}else{
////    defineResClsName = "String";
////}
////"	class SuccessContent{");
////"		public " + defineResClsName + " Content;");
////"	}");
//
//            line(sb);
//            "	public "+clsName+"(){");
//            "	    initProxy();");
//            "	}");
//            "	");
//            "	public "+clsName+"(ProviderContext pContext){");
//            "	    initProxy(pContext);");
//            "	}");
//            line(sb);
//
//            //region public void doRequest
//            "	public void doRequest(");
//            "	    IProxyDataProvider provider,");
//            if(gReqClassName != null){
//                "	    "+gReqClassName+" gInput,");
//            }
//            if(pReqClassName != null){
//                "	    "+pReqClassName+" pInput,");
//            }
//            "	    final IOnProxyDoneListener<"+defineResClsName+"> onListener");
//            "	){");
//            line(sb);
//            "	    Promiser $q = $doRequest(provider,");
//            if(gReqClassName != null){
//                "	    gInput,");
//            }
//            if(pReqClassName != null){
//                "	    pInput,");
//            }
//            "	    onListener);");
//            "	    if($q != null)");
//            "	        $q.run();");
//            "	}");
//            line(sb);
//            //endregion
//            //region public void $doRequestForString
//            "	public Promiser $doRequestForString(");
//            "	    IProxyDataProvider provider,");
//            if(gReqClassName != null){
//                "	    "+gReqClassName+" gInput,");
//            }
//            if(pReqClassName != null){
//                "	    "+pReqClassName+" pInput,");
//            }
//            "	    final IOnProxyDoneForStringListener onListener");
//            "	){");
//            line(sb);
//            "	    String url = getApiURL();");
//            "	    ProviderContext pContext = getPContext();");
//            "	    pContext.setHost(url);");
//            "	    ");
//
//            "	    IHttpHelperListener l = new IHttpHelperListener() {");
//            "	            @Override");
//            "	            public void onSuccess(String result) {");
//            line(sb);
//            "	                onReSuccess(result);");
//            line(sb);
//            "	            }");
//            line(sb);
//            "	            @Override");
//            "	            public boolean onReSuccess(String result) {");
//            line(sb);
//            "	                ResBody body = resovleJson(result,ResBody.class);");
//            "	                boolean reBoolean = true;");
//            line(sb);
//            "	                if(body == null){");
//            line(sb);
//            "	                    reBoolean = onListener.error(");
//            "	                        -1,");
//            "	                        SERVICE_ERROR_DATA,");
//            "	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//            "	                }else if(checkServiceState(body.State)){");
//            line(sb);
//            "	                    onListener.success(result);");
//            "	                }else{");
//            line(sb);
//            "	                    reBoolean = onListener.error(");
//            "	                        body.State,");
//            "	                        body.Message,");
//            "	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//            "	                }");
//            line(sb);
//            "	                onListener.done();");
//            "	                return reBoolean;");
//            line(sb);
//            "	            }");
//            "	        ");
//            "	            @Override");
//            "	            public void onError(int stateCode, String message) {");
//            "	                onListener.error(stateCode, message, IOnProxyDoneListener.ERR_TYPE_NET);");
//            "	                onListener.done();");
//            "	            }");
//            "         };");
//
//            "	        ");
//            "         Promiser $q;");
//            "         if(method.toUpperCase().equals(\"GET\")){");
//            "             $q = provider.get(pContext,");
//            "             "+(gReqClassName != null?"gInput":"null")+",");
//            "             "+(pReqClassName != null?"pInput":"null")+",");
//            "             l);");
//            "         }else{");
//            "             $q = provider.post(pContext,");
//            "             "+(gReqClassName != null?"gInput":"null")+",");
//            "             "+(pReqClassName != null?"pInput":"null")+",");
//            "             l);");
//            "         }");
//            line(sb);
//            "         onListener.setPromiser($q);");
//            "         return $q;");
//            "     }");
//            "	    ");
//            //endregion
//            //region public Promiser $doRequest
//            "	public Promiser $doRequest(");
//            "	    IProxyDataProvider provider,");
//            if(gReqClassName != null){
//                "	    "+gReqClassName+" gInput,");
//            }
//            if(pReqClassName != null){
//                "	    "+pReqClassName+" pInput,");
//            }
//            "	    final IOnProxyDoneListener<"+defineResClsName+"> onListener");
//            "	){");
//            line(sb);
//            "	    String url = getApiURL();");
//            "	    ProviderContext pContext = getPContext();");
//            "	    pContext.setHost(url);");
//            "	    ");
//
//            "	    IHttpHelperListener l = new IHttpHelperListener() {");
//            "	            @Override");
//            "	            public void onSuccess(String result) {");
//            line(sb);
//            "	                onReSuccess(result);");
//            line(sb);
//            "	            }");
//            line(sb);
//            "	            @Override");
//            "	            public boolean onReSuccess(String result) {");
//            line(sb);
//            "	                ResBody body = resovleJson(result,ResBody.class);");
//            line(sb);
//            "	                boolean reBoolean = true;");
//            "	                if(body == null){");
//            line(sb);
//            "	                    reBoolean = onListener.error(");
//            "	                        -1,");
//            "	                        SERVICE_ERROR_DATA,");
//            "	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//            "	                }else if(checkServiceState(body.State)){");
//            line(sb);
//            "	                    "+defineResClsName+" output = resovleJson(result,"+defineResClsName+".class);");
//            "	                    onListener.success(output);");
//            "	                }else{");
//            line(sb);
//            "	                    reBoolean = onListener.error(");
//            "	                        body.State,");
//            "	                        body.Message,");
//            "	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//            "	                }");
//            line(sb);
//            "	                onListener.done();");
//            "	                return reBoolean;");
//            line(sb);
//            "	            }");
//            "	        ");
//            "	            @Override");
//            "	            public void onError(int stateCode, String message) {");
//            "	                onListener.error(stateCode, message, IOnProxyDoneListener.ERR_TYPE_NET);");
//            "	                onListener.done();");
//            "	            }");
//            "         };");
//
//            "	        ");
//            "         Promiser $q;");
//            "         if(method.toUpperCase().equals(\"GET\")){");
//            "             $q = provider.get(pContext,");
//            "             "+(gReqClassName != null?"gInput":"null")+",");
//            "             "+(pReqClassName != null?"pInput":"null")+",");
//            "             l);");
//            "         }else{");
//            "             $q = provider.post(pContext,");
//            "             "+(gReqClassName != null?"gInput":"null")+",");
//            "             "+(pReqClassName != null?"pInput":"null")+",");
//            "             l);");
//            "         }");
//            line(sb);
//            "         onListener.setPromiser($q);");
//            "         return $q;");
//            "     }");
//            "	    ");
//            //endregion
//            //region public String getApiURL
//            "     public String getApiURL(){");
//            "         return getHost() + apiUrl;");
//            "     }");
//            "	    ");
//            //endregion
//            //region public String getAction
//            "     public String getAction(){");
//            "         return null;");
//            "     }");
//            line(sb);
//            //endregion
//            "}");
////=========================
//        }
//
//        return sb.toString();
//    }
//    @Override
//    public String getFileExName() {
//        return "java";
//    }
//}
