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
//                            String[] importPkgs,
//                            String apiUrl,
//                            String method,
//                            ClassStructureInfo proxyClass,
//                            String gReqClassName,
//                            String pReqClassName,
//                            String resClassName){
//StringBuilder sb = new StringBuilder();
//
//appendLine(sb,"package " + proxyClass.getNameSpace() + ";");
//line(sb);
//appendLine(sb,"import java.util.ArrayList;");
//line(sb);
//        for (String s :
//                importPkgs) {
//            appendLine(sb,"import " + s + ";");
//        }
////appendLine(sb,"import "+helperRootNS+".common.BaseProxy;");
////appendLine(sb,"import "+helperRootNS+".helper.IHttpHelperListener;");
////appendLine(sb,"import "+helperRootNS+".helper.IOnProxyDoneListener;");
////appendLine(sb,"import "+helperRootNS+".helper.IProxyDataProvider;");
////appendLine(sb,"import "+helperRootNS+".helper.ProviderContext;");
////appendLine(sb,"import "+helperRootNS+".promise.Promiser;");
//
//line(sb);
//        String clsName = StringHelper.upperCaseFirst(proxyClass.getClassName());
//appendLine(sb,"public class " + StringHelper.upperCaseFirst(proxyClass.getClassName()) + " extends BaseProxy {");
//{
//    line(sb);
//appendLine(sb,"	private String apiUrl = \""+apiUrl+"\";");
//appendLine(sb,"	private String method = \""+method+"\";");
////    line(sb);
//String defineResClsName = resClassName;
////if(resClassName != null){
////    defineResClsName = resClassName;
////}else{
////    defineResClsName = "String";
////}
////appendLine(sb,"	class SuccessContent{");
////appendLine(sb,"		public " + defineResClsName + " Content;");
////appendLine(sb,"	}");
//
//line(sb);
//appendLine(sb,"	public "+clsName+"(){");
//appendLine(sb,"	    initProxy();");
//appendLine(sb,"	}");
//appendLine(sb,"	");
//appendLine(sb,"	public "+clsName+"(ProviderContext pContext){");
//appendLine(sb,"	    initProxy(pContext);");
//appendLine(sb,"	}");
//line(sb);
//
//    //region public void doRequest
//appendLine(sb,"	public void doRequest(");
//appendLine(sb,"	    IProxyDataProvider provider,");
//if(gReqClassName != null){
//appendLine(sb,"	    "+gReqClassName+" gInput,");
//}
//if(pReqClassName != null){
//appendLine(sb,"	    "+pReqClassName+" pInput,");
//}
//appendLine(sb,"	    final IOnProxyDoneListener<"+defineResClsName+"> onListener");
//appendLine(sb,"	){");
//    line(sb);
//appendLine(sb,"	    Promiser $q = $doRequest(provider,");
//if(gReqClassName != null){
//    appendLine(sb,"	    gInput,");
//}
//if(pReqClassName != null){
//    appendLine(sb,"	    pInput,");
//}
//appendLine(sb,"	    onListener);");
//appendLine(sb,"	    if($q != null)");
//appendLine(sb,"	        $q.run();");
//appendLine(sb,"	}");
//    line(sb);
//    //endregion
//    //region public void $doRequestForString
//    appendLine(sb,"	public Promiser $doRequestForString(");
//    appendLine(sb,"	    IProxyDataProvider provider,");
//    if(gReqClassName != null){
//        appendLine(sb,"	    "+gReqClassName+" gInput,");
//    }
//    if(pReqClassName != null){
//        appendLine(sb,"	    "+pReqClassName+" pInput,");
//    }
//    appendLine(sb,"	    final IOnProxyDoneForStringListener onListener");
//    appendLine(sb,"	){");
//    line(sb);
//    appendLine(sb,"	    String url = getApiURL();");
//    appendLine(sb,"	    ProviderContext pContext = getPContext();");
//    appendLine(sb,"	    pContext.setHost(url);");
//    appendLine(sb,"	    ");
//
//    appendLine(sb,"	    IHttpHelperListener l = new IHttpHelperListener() {");
//    appendLine(sb,"	            @Override");
//    appendLine(sb,"	            public void onSuccess(String result) {");
//    line(sb);
//    appendLine(sb,"	                onReSuccess(result);");
//    line(sb);
//    appendLine(sb,"	            }");
//    line(sb);
//    appendLine(sb,"	            @Override");
//    appendLine(sb,"	            public boolean onReSuccess(String result) {");
//    line(sb);
//    appendLine(sb,"	                ResBody body = resovleJson(result,ResBody.class);");
//    appendLine(sb,"	                boolean reBoolean = true;");
//    line(sb);
//    appendLine(sb,"	                if(body == null){");
//    line(sb);
//    appendLine(sb,"	                    reBoolean = onListener.error(");
//    appendLine(sb,"	                        -1,");
//    appendLine(sb,"	                        SERVICE_ERROR_DATA,");
//    appendLine(sb,"	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//    appendLine(sb,"	                }else if(checkServiceState(body.State)){");
//    line(sb);
//    appendLine(sb,"	                    onListener.success(result);");
//    appendLine(sb,"	                }else{");
//    line(sb);
//    appendLine(sb,"	                    reBoolean = onListener.error(");
//    appendLine(sb,"	                        body.State,");
//    appendLine(sb,"	                        body.Message,");
//    appendLine(sb,"	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//    appendLine(sb,"	                }");
//    line(sb);
//    appendLine(sb,"	                onListener.done();");
//    appendLine(sb,"	                return reBoolean;");
//    line(sb);
//    appendLine(sb,"	            }");
//    appendLine(sb,"	        ");
//    appendLine(sb,"	            @Override");
//    appendLine(sb,"	            public void onError(int stateCode, String message) {");
//    appendLine(sb,"	                onListener.error(stateCode, message, IOnProxyDoneListener.ERR_TYPE_NET);");
//    appendLine(sb,"	                onListener.done();");
//    appendLine(sb,"	            }");
//    appendLine(sb,"         };");
//
//    appendLine(sb,"	        ");
//    appendLine(sb,"         Promiser $q;");
//    appendLine(sb,"         if(method.toUpperCase().equals(\"GET\")){");
//    appendLine(sb,"             $q = provider.get(pContext,");
//    appendLine(sb,"             "+(gReqClassName != null?"gInput":"null")+",");
//    appendLine(sb,"             "+(pReqClassName != null?"pInput":"null")+",");
//    appendLine(sb,"             l);");
//    appendLine(sb,"         }else{");
//    appendLine(sb,"             $q = provider.post(pContext,");
//    appendLine(sb,"             "+(gReqClassName != null?"gInput":"null")+",");
//    appendLine(sb,"             "+(pReqClassName != null?"pInput":"null")+",");
//    appendLine(sb,"             l);");
//    appendLine(sb,"         }");
//    line(sb);
//    appendLine(sb,"         onListener.setPromiser($q);");
//    appendLine(sb,"         return $q;");
//    appendLine(sb,"     }");
//    appendLine(sb,"	    ");
//    //endregion
//    //region public Promiser $doRequest
//appendLine(sb,"	public Promiser $doRequest(");
//appendLine(sb,"	    IProxyDataProvider provider,");
//if(gReqClassName != null){
//    appendLine(sb,"	    "+gReqClassName+" gInput,");
//}
//if(pReqClassName != null){
//    appendLine(sb,"	    "+pReqClassName+" pInput,");
//}
//appendLine(sb,"	    final IOnProxyDoneListener<"+defineResClsName+"> onListener");
//appendLine(sb,"	){");
//    line(sb);
//appendLine(sb,"	    String url = getApiURL();");
//appendLine(sb,"	    ProviderContext pContext = getPContext();");
//appendLine(sb,"	    pContext.setHost(url);");
//appendLine(sb,"	    ");
//
//appendLine(sb,"	    IHttpHelperListener l = new IHttpHelperListener() {");
//appendLine(sb,"	            @Override");
//appendLine(sb,"	            public void onSuccess(String result) {");
//    line(sb);
//appendLine(sb,"	                onReSuccess(result);");
//    line(sb);
//appendLine(sb,"	            }");
//    line(sb);
//appendLine(sb,"	            @Override");
//appendLine(sb,"	            public boolean onReSuccess(String result) {");
//    line(sb);
//appendLine(sb,"	                ResBody body = resovleJson(result,ResBody.class);");
//    line(sb);
//appendLine(sb,"	                boolean reBoolean = true;");
//appendLine(sb,"	                if(body == null){");
//    line(sb);
//appendLine(sb,"	                    reBoolean = onListener.error(");
//appendLine(sb,"	                        -1,");
//appendLine(sb,"	                        SERVICE_ERROR_DATA,");
//appendLine(sb,"	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//appendLine(sb,"	                }else if(checkServiceState(body.State)){");
//    line(sb);
//appendLine(sb,"	                    "+defineResClsName+" output = resovleJson(result,"+defineResClsName+".class);");
//appendLine(sb,"	                    onListener.success(output);");
//appendLine(sb,"	                }else{");
//    line(sb);
//appendLine(sb,"	                    reBoolean = onListener.error(");
//appendLine(sb,"	                        body.State,");
//appendLine(sb,"	                        body.Message,");
//appendLine(sb,"	                        IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//appendLine(sb,"	                }");
//    line(sb);
//appendLine(sb,"	                onListener.done();");
//appendLine(sb,"	                return reBoolean;");
//    line(sb);
//appendLine(sb,"	            }");
//appendLine(sb,"	        ");
//appendLine(sb,"	            @Override");
//appendLine(sb,"	            public void onError(int stateCode, String message) {");
//appendLine(sb,"	                onListener.error(stateCode, message, IOnProxyDoneListener.ERR_TYPE_NET);");
//appendLine(sb,"	                onListener.done();");
//appendLine(sb,"	            }");
//appendLine(sb,"         };");
//
//appendLine(sb,"	        ");
//appendLine(sb,"         Promiser $q;");
//appendLine(sb,"         if(method.toUpperCase().equals(\"GET\")){");
//appendLine(sb,"             $q = provider.get(pContext,");
//appendLine(sb,"             "+(gReqClassName != null?"gInput":"null")+",");
//appendLine(sb,"             "+(pReqClassName != null?"pInput":"null")+",");
//appendLine(sb,"             l);");
//appendLine(sb,"         }else{");
//appendLine(sb,"             $q = provider.post(pContext,");
//appendLine(sb,"             "+(gReqClassName != null?"gInput":"null")+",");
//appendLine(sb,"             "+(pReqClassName != null?"pInput":"null")+",");
//appendLine(sb,"             l);");
//appendLine(sb,"         }");
//    line(sb);
//appendLine(sb,"         onListener.setPromiser($q);");
//appendLine(sb,"         return $q;");
//appendLine(sb,"     }");
//appendLine(sb,"	    ");
//    //endregion
//    //region public String getApiURL
//appendLine(sb,"     public String getApiURL(){");
//appendLine(sb,"         return getHost() + apiUrl;");
//appendLine(sb,"     }");
//appendLine(sb,"	    ");
//    //endregion
//    //region public String getAction
//appendLine(sb,"     public String getAction(){");
//appendLine(sb,"         return null;");
//appendLine(sb,"     }");
//    line(sb);
//    //endregion
//appendLine(sb,"}");
////=========================
//}
//
//        return sb.toString();
//    }
//    @Override
//    public String getFileExName() {
//        return "java";
//    }
//}
