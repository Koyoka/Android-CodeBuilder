package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core.scriptTemplate;


import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBClassStructureInfo;

public abstract class IDbScriptTemplate {
	
	public abstract String getScript(DBClassStructureInfo cls);
	public abstract String getFileExName();

	protected void appendLine(StringBuilder sb,String s){
		sb.append(s+"\r\n");
	}
	protected void line(StringBuilder sb){
		sb.append("\r\n");
	}
}
