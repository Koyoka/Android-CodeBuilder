//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate;
//
//
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//public class TGCMAppProxyTemplate extends IScriptTemplate {
//
//	public String getScript(String helperRootNS,String serviceName,String action,
//			ClassStructureInfo proxyClass,
//			String reqClassName,
//			String resClassName,
//			boolean reqIsArray,
//			boolean resIsArray){
//		StringBuilder sb = new StringBuilder();
//
//appendLine(sb,"package " + proxyClass.getNameSpace() + ";");
//line(sb);
//appendLine(sb,"import java.util.ArrayList;");
//line(sb);
//appendLine(sb,"import "+helperRootNS+".common.BaseProxy;");
//appendLine(sb,"import "+helperRootNS+".helper.IDataProvider;");
//appendLine(sb,"import "+helperRootNS+".helper.IOnProxyDoneListener;");
//appendLine(sb,"import "+helperRootNS+".promise.JPromiser;");
////appendLine(sb,"import "+helperRootNS+".dto.BaseResponseBody;");
//line(sb);
////appendLine(sb,"//reqClass:"+(reqClass==null?"":reqClass.getClassName()));
////appendLine(sb,"//resClass:"+resClass.getClassName());
//
//appendLine(sb,"public class " + StringHelper.upperCaseFirst(proxyClass.getClassName()) + " extends BaseProxy {");
//{
//
////line(sb);
////appendLine(sb,"	class ResponseBody extends BaseResponseBody{");
////String resClassName = resClass.getClassName();
//
//
//line(sb);
//line(sb);
//appendLine(sb,"	private String serviceName = \""+serviceName+"\";");
//appendLine(sb,"	private String action = \""+action+"\";");
//appendLine(sb,"	");
//appendLine(sb,"	class Body extends "+resClassName+"{");
//appendLine(sb,"		public int State;");
//appendLine(sb,"		public String Message;");
//appendLine(sb,"	}");
//line(sb);
//if(reqClassName != null){
//appendLine(sb,"	public void doRequest("+reqClassName+" input,IDataProvider httpHelper,final IOnProxyDoneListener<"+resClassName+"> onListener){");
//appendLine(sb,"		JPromiser $q = $doRequest(input,httpHelper,onListener);");
//}else{
//	appendLine(sb,"	public void doRequest(IDataProvider httpHelper,final IOnProxyDoneListener<"+resClassName+"> onListener){");
//	appendLine(sb,"		JPromiser $q = $doRequest(httpHelper,onListener);");
//}
//appendLine(sb,"		if($q != null){");
//appendLine(sb,"			$q.run();");
//appendLine(sb,"		}");
//appendLine(sb,"	}");
//line(sb);
//if(reqClassName != null){
//	appendLine(sb,"	public JPromiser $doRequest("+reqClassName+" input,IDataProvider httpHelper,final IOnProxyDoneListener<"+resClassName+"> onListener){");
//}else{
//	appendLine(sb,"	public JPromiser $doRequest(IDataProvider httpHelper,final IOnProxyDoneListener<"+resClassName+"> onListener){");
//}
//appendLine(sb,"		String url = getApiURL();");
//appendLine(sb,"		JPromiser $q =");
//if(reqClassName != null){
//appendLine(sb,"		httpHelper.Post(url,getAction(), input, new IDataProvider.IHttpHelperListener() {");
//}else{
//appendLine(sb,"		httpHelper.Post(url,getAction(), null, new IDataProvider.IHttpHelperListener() {");
//}
//appendLine(sb,"			");
//appendLine(sb,"			@Override");
//appendLine(sb,"			public void onSuccess(String result) {");
////appendLine(sb,"				"+resClassName+" body = resovleJson(result,"+resClassName+".class);");
//appendLine(sb,"				Body body = resovleJson(result,Body.class);");
//appendLine(sb,"				if(body == null){");
//appendLine(sb,"					onListener.error(");
//appendLine(sb,"						-1, ");
//appendLine(sb,"						SERVICE_ERROR_DATA,");
//appendLine(sb,"						IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//appendLine(sb,"				}else if(body.State==0){");
//appendLine(sb,"					onListener.success(body);");
////if(resClass != null){
////	appendLine(sb,"					onListener.success(body.Content.Data);");
////}else{
////	appendLine(sb,"					onListener.success(body.Content);");
////}
//appendLine(sb,"				}else{");
//appendLine(sb,"					onListener.error(");
//appendLine(sb,"						body.State,");
//appendLine(sb,"						body.Message,");
//appendLine(sb,"					IOnProxyDoneListener.ERR_TYPE_SERVICE);");
//appendLine(sb,"				}");
//appendLine(sb,"				onListener.done();");
////appendLine(sb,"				ResponseBody body = resovleJson(result,ResponseBody.class);");
////appendLine(sb,"				onListener.success(body.Content.Data);");
////appendLine(sb,"				onListener.done();");
//appendLine(sb,"			}");
//appendLine(sb,"			");
//appendLine(sb,"			@Override");
//appendLine(sb,"			public void onError(int stateCode, String message) {");
//appendLine(sb,"				onListener.error(stateCode, message, IOnProxyDoneListener.ERR_TYPE_NET);");
//appendLine(sb,"				onListener.done();");
//appendLine(sb,"			}");
//appendLine(sb,"			");
//appendLine(sb,"		});");
//appendLine(sb,"		return $q;");
//appendLine(sb,"	}");
//appendLine(sb,"		");
//appendLine(sb,"	public String getApiURL(){");
//appendLine(sb,"		return getHost() + serviceName;");
//appendLine(sb,"	}");
//appendLine(sb,"	");
//appendLine(sb,"	public String getAction(){");
//appendLine(sb,"		return action;");
//appendLine(sb,"	}");
//line(sb);
//}
//appendLine(sb,"}");
//
//		return sb.toString();
//
//	}
//
//	@Override
//	public String getScript(ClassStructureInfo... clsStructs) {
//
//		ClassStructureInfo clsStruct = clsStructs[0];
//		StringBuilder sb = new StringBuilder();
//
//appendLine(sb,"package " + clsStruct.getNameSpace() + ";");
////line(sb);
////appendLine(sb,"import java.util.ArrayList;");
////appendLine(sb,"import java.util.Date;");
//line(sb);
//appendLine(sb,"public class " + StringHelper.upperCaseFirst(clsStruct.getClassName()) + " {");
//line(sb);
////for(ClassFieldInfo item : clsStruct.getFieldList()){
////
////	appendLine(sb,"	private " + getScriptType(item) + " " + item.getFieldName() + ";");
////}
////line(sb);
////for(ClassFieldInfo item : clsStruct.getFieldList()){
////
////	appendLine(sb,"	public void set" + StringHelper.upperCaseFirst(item.getFieldName()) + "("+ getScriptType(item) + " " + StringHelper.lowerCaseFirst(item.getFieldName()) +  "){");
////	appendLine(sb,"		this."+item.getFieldName() + " = " + StringHelper.lowerCaseFirst(item.getFieldName()) + ";" );
////	appendLine(sb,"	}");
////
////	appendLine(sb,"	public "+getScriptType(item)+" get" + StringHelper.upperCaseFirst(item.getFieldName()) + "(){");
////	appendLine(sb,"		return this."+item.getFieldName() + ";" );
////	appendLine(sb,"	}");
////}
////line(sb);
//appendLine(sb,"}");
//
//
//		return sb.toString();
//	}
//
//	@Override
//	public String getFileExName() {
//		return "java";
//	}
//
//}
////if(resClass != null){
////resClassName = resClass.getClassName();
////appendLine(sb,"		private ContentBody Content;");
////appendLine(sb,"		public ContentBody getContent() {");
////appendLine(sb,"			return this.Content;");
////appendLine(sb,"		}");
////appendLine(sb,"		public void setContent(ContentBody content) {");
////appendLine(sb,"			this.Content = content;");
////appendLine(sb,"		}");
////}else{
////resClassName = "String";
////appendLine(sb,"		private String Content;");
////appendLine(sb,"		public String getContent() {");
////appendLine(sb,"			return this.Content;");
////appendLine(sb,"		}");
////appendLine(sb,"		public void setContent(String content) {");
////appendLine(sb,"			this.Content = content;");
////appendLine(sb,"		}");
////}
////appendLine(sb,"	}");
//
////if(resIsArray){
////resClassName = "ArrayList<"+resClassName+">";
////}
//
////line(sb);
////if(resClass != null){
////appendLine(sb,"	class ContentBody{");
////appendLine(sb,"		private "+resClassName+" Data;");
////appendLine(sb,"		public "+resClassName+" getData() {");
////appendLine(sb,"			return this.Data;");
////appendLine(sb,"		}");
////appendLine(sb,"		public void setData("+resClassName+" data) {");
////appendLine(sb,"			this.Data = data;");
////appendLine(sb,"		}");
////appendLine(sb,"	}");
////}