package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core.scriptTemplate;


import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBClassStructureInfo;

public class OrmDbScriptTemplate extends IDbScriptTemplate{


	@Override
	public String getScript(DBClassStructureInfo clsStruct) {
		StringBuilder sb = new StringBuilder();
		

appendLine(sb,"package " + clsStruct.getNameSpace() + ";");
line(sb);
appendLine(sb,"import java.util.ArrayList;");
appendLine(sb,"import java.util.Date;");
line(sb);
appendLine(sb,"import com.j256.ormlite.table.DatabaseTable;");
appendLine(sb,"import com.j256.ormlite.field.DatabaseField;");
appendLine(sb,"import com.xxxx.base.InsBaseModel;");

line(sb);
appendLine(sb,clsStruct.getClassAttriStr());
appendLine(sb,"public class " + (clsStruct.getClassName()) + "  extends InsBaseModel{");
line(sb);
appendLine(sb,"	public final static String TABLE_NAME = " + "\""+clsStruct.getTableName()+"\";");
line(sb);
for(DBClassFieldInfo item : clsStruct.getFieldList()){
	appendLine(sb,"	public final static String COL_"+item.getDbFieldName().toUpperCase() + " = " + "\""+item.getDbFieldName()+"\";");
}
line(sb);
for(DBClassFieldInfo item : clsStruct.getFieldList()){
	appendLine(sb,"	"+item.getFieldAttriStr());
	appendLine(sb,"	private " + (item.getFieldType()) + " " + item.getFieldName() + ";");
}
line(sb);
for(DBClassFieldInfo item : clsStruct.getFieldList()){
	appendLine(sb,"	public void set" + StringHelper.upperCaseFirst(item.getFieldName()) + "("+ (item.getFieldType()) + " " + StringHelper.lowerCaseFirst(item.getFieldName()) +  "){");
	appendLine(sb,"		this."+item.getFieldName() + " = " + StringHelper.lowerCaseFirst(item.getFieldName()) + ";" );
	appendLine(sb,"	}");
	appendLine(sb,"	public "+ (item.getFieldType()) +" get" + StringHelper.upperCaseFirst(item.getFieldName()) + "(){");
	appendLine(sb,"		return this."+item.getFieldName() + ";" );
	appendLine(sb,"	}");
}
line(sb);
appendLine(sb,"/*");
for(DBClassFieldInfo item : clsStruct.getFieldList()){
appendLine(sb,"set" + StringHelper.upperCaseFirst(item.getFieldName()) + "();");
}
		line(sb);
for(DBClassFieldInfo item : clsStruct.getFieldList()){
	appendLine(sb,"get" + StringHelper.upperCaseFirst(item.getFieldName()) + "();");
}
appendLine(sb,"*/");
appendLine(sb,"}");
		return sb.toString();
	}

	@Override
	public String getFileExName() {
		return "java";
	}

}
