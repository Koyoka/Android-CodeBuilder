package com.eleven.codebuilder.javaAutomationTools.codeCreator.core.scriptTemplate;


import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.statics.ClassFieldBaseTypeEnum;

public abstract class IScriptTemplate {

	public abstract String getScript(ClassStructureInfo... clsStructs);
	public abstract String getProxyScript(//String helperRootNS,
							String[] importPkgs,
							String apiUrl,
							String method,
							ClassStructureInfo proxyClass,
							String gReqClassName,
							String pReqClassName,
							String resClassName);

	public abstract String getFileExName();
	
	protected void appendLine(StringBuilder sb,String s){
		sb.append(s+"\r\n");
	}
	protected void line(StringBuilder sb){
		sb.append("\r\n");
	}
	
	protected String getScriptType(ClassFieldInfo item){
		String filedType = item.getFieldType();
		if(item.isArray()){
			if(false){
				//nothing
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.BOOLEAN)){
				filedType = "ArrayList<Boolean>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.DOUBLE)){
				filedType = "ArrayList<Double>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.FLOAT)){
				filedType = "ArrayList<Float>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.INT)){
				filedType = "ArrayList<Integer>";
			}else if(item.getFieldType().equals(ClassFieldBaseTypeEnum.LONG)){
				filedType = "ArrayList<Long>";
			}else{
				filedType = "ArrayList<"+filedType+">";
			}
			
		}
		return filedType;
	}
}
