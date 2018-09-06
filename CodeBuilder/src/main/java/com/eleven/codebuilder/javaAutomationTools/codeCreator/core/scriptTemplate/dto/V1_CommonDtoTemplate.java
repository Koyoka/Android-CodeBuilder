//package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.dto;
//
//
//import com.eleven.codebuilder.common.StringHelper;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate.IScriptTemplate;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
//import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
//
//public class V1_CommonDtoTemplate extends IScriptTemplate {
//
//	protected String checkKeyWord(String s){
//		String[] keywords = getKeyWords();
//		for (String k :
//				keywords) {
//			if(k.equals(s)){
//				return "m"+s;
//			}
//		}
//		return s;
//	}
//
//	protected String[] getKeyWords() {
//		return new String[]{
//				"extends"
//		};
//	}
//
//	@Override
//	public String getScript(ClassStructureInfo... clsStructs) {
//		ClassStructureInfo clsStruct = clsStructs[0];
//		StringBuilder sb = new StringBuilder();
//
//appendLine(sb,"package " + clsStruct.getNameSpace() + ";");
//line(sb);
//appendLine(sb,"import java.util.ArrayList;");
//appendLine(sb,"import java.util.Date;");
//appendLine(sb,"import java.util.HashMap;");
//appendLine(sb,"import java.util.Map;");
//line(sb);
//appendLine(sb,"import com.eleven.httpvolleypromiser.proxy.base.BaseModelDto;");
//line(sb);
//appendLine(sb,"public class " + StringHelper.upperCaseFirst(clsStruct.getClassName()) + " extends BaseModelDto{");
//line(sb);
//for(ClassFieldInfo item : clsStruct.getFieldList()){
//	appendLine(sb,"	private " + getScriptType(item) + " " + item.getFieldName() + ";");
//}
//line(sb);
//for(ClassFieldInfo item : clsStruct.getFieldList()){
//
//	appendLine(sb,"	public void set" + StringHelper.upperCaseFirst(item.getFieldName()) + "("+ getScriptType(item) + " " + checkKeyWord(StringHelper.lowerCaseFirst(item.getFieldName())) +  "){");
//	appendLine(sb,"		this."+item.getFieldName() + " = " + checkKeyWord(StringHelper.lowerCaseFirst(item.getFieldName())) + ";" );
//	appendLine(sb,"	}");
//
//	appendLine(sb,"	public "+getScriptType(item)+" get" + StringHelper.upperCaseFirst(item.getFieldName()) + "(){");
//	appendLine(sb,"		return this."+item.getFieldName() + ";" );
//	appendLine(sb,"	}");
//}
//line(sb);
//appendLine(sb,"	@Override");
//appendLine(sb,"	public Map<String, String> getFieldMap() {");
//line(sb);
//appendLine(sb,"		Map<String, String> map = new HashMap<String, String>();  ");
//for(ClassFieldInfo item : clsStruct.getFieldList()){
//appendLine(sb,"		map.put(\""+item.getFieldName()+"\", safeGetHttpData(this."+item.getFieldName() + "));");
//}
//appendLine(sb,"		return map;");
//appendLine(sb,"	}");
//line(sb);
//appendLine(sb,"}");
//
//
//		return sb.toString();
//	}
//
//	@Override
//	public String getProxyScript(String[] importPkgs, String apiUrl, String method, ClassStructureInfo proxyClass, String gReqClassName, String pReqClassName, String resClassName) {
//		return null;
//	}
//
//	@Override
//	public String getFileExName() {
//		return "java";
//	}
//
////	private String getScriptType(ClassFieldInfo item){
////		String filedType = item.getFieldType();
////		if(item.isArray()){
////			if(false){
////				//nothing
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.BOOLEAN)){
////				filedType = "ArrayList<Boolean>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.DOUBLE)){
////				filedType = "ArrayList<Double>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.FLOAT)){
////				filedType = "ArrayList<Float>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.INT)){
////				filedType = "ArrayList<Integer>";
////			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.LONG)){
////				filedType = "ArrayList<Long>";
////			}else{
////				filedType = "ArrayList<"+filedType+">";
////			}
////
////		}
////		return filedType;
////	}
//
//}
