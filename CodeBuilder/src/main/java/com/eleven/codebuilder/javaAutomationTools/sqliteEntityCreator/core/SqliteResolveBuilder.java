package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core;


import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core.scriptTemplate.IDbScriptTemplate;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBClassStructureInfo;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.DBTableInfo;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model.SqliteTableInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SqliteResolveBuilder {
	private IDbScriptTemplate mIDbScriptTemplate;
	public static SqliteResolveBuilder getInstance(){
		return new SqliteResolveBuilder();
	}
	
	public void builder(String dbPath,String savePath,String ns,IDbScriptTemplate temp){
		mIDbScriptTemplate = temp;

		
		ArrayList<SqliteTableInfo> tabInfoList =
				getTableInfomation(dbPath);
		PrintHelper.print(tabInfoList);
		ArrayList<DBTableInfo> dbTabInfoList =
				resolveTableInfomation(tabInfoList);
		ArrayList<DBClassStructureInfo> buildTableClass =
				buildTableClass(dbTabInfoList,ns);
		PrintHelper.print(buildTableClass);
		
		
		for (DBClassStructureInfo item : buildTableClass) {
			
			PrintHelper.print("========================================================================");
			String script = mIDbScriptTemplate.getScript(item);
			saveToDir(savePath,item.getClassName(),script,mIDbScriptTemplate);
			PrintHelper.print(script);
		
		}
		 
		
	}
	
	private ArrayList<SqliteTableInfo> getTableInfomation(String dbPath){
		
		ArrayList<SqliteTableInfo> tabInfoList = new ArrayList<>();
		
		Connection connection = null;
		 try {
			 connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
			 
			 Statement statement = connection.createStatement();
			 statement.setQueryTimeout(30);  
			
			 ResultSet rs = statement.executeQuery("select * from [SQLITE_MASTER]");
			 while(rs.next()){
				
				 String tabName = rs.getString("name");
				 if(!tabName.toLowerCase().equals("sqlite_sequence")
						 && !tabName.toLowerCase().equals("sqlite_stat1")){
					 
					 String tabSql = rs.getString("sql");

					 if(tabSql != null){
//						 tabSql = tabSql.replaceFirst("\"","[").replaceFirst("\"","]");

						 SqliteTableInfo tabInfo = new SqliteTableInfo();
						 tabInfo.setTabName(tabName);
						 tabInfo.setTabSql(tabSql);
						 tabInfoList.add(tabInfo);
					 }

				 }
			 }
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 finally
       {
		  try
		  {
		    if(connection != null)
		      connection.close();
		  }
		  catch(SQLException e)
		  {
		    // connection close failed.
		    System.err.println(e);
		  }
       }
		
		return tabInfoList;
	}
	
	private ArrayList<DBTableInfo> resolveTableInfomation(ArrayList<SqliteTableInfo> tabInfoList ){
		String regEx = "[\\((][\\s\\S]*[\\))]";
		Pattern pat = Pattern.compile(regEx);  
	
//		ArrayList<String[]> fieldInfosGroup = new ArrayList<>();
		for (SqliteTableInfo item : tabInfoList) {
			PrintHelper.print(item.getTabSql() + "   ....");
//			if(item.getTabSql() != null){
				Matcher mat = pat.matcher(item.getTabSql());
			 	PrintHelper.print(item.getTabName());
				if(mat.find()){
					String s = mat.group().replace("(", "").replace(")", "").replace("\r\n", "");
					String[] fieldInfos = s.split(",");
					item.setFieldSqls(fieldInfos);
//				 PrintHelper.print(s);
					continue;
				}
//			}


		}
		
		ArrayList<DBTableInfo> dbTableInfoList = new ArrayList<>();
		for (SqliteTableInfo item : tabInfoList) {
			DBTableInfo dbItem = new DBTableInfo();
			dbItem.setTableName(item.getTabName());
			
			ArrayList<DBTableInfo.DBTableFieldInfo> dbFieldInfos = new ArrayList<>();
			for (String sql : item.getFieldSqls()) {
				DBTableInfo.DBTableFieldInfo dbFiled =
						getDBFieldInfo(sql);
				dbFieldInfos.add(dbFiled);
			}
			
			dbItem.setFieldList(dbFieldInfos);
			
			dbTableInfoList.add(dbItem);
		}
		
		
		return dbTableInfoList;
		
	}
	
	private DBTableInfo.DBTableFieldInfo getDBFieldInfo(String sql){
		DBTableInfo.DBTableFieldInfo field = new DBTableInfo.DBTableFieldInfo();
		
		String[] defineStr = sql.split(" ");
		String fieldName = "";
		{
			fieldName = defineStr[0].replace("[", "").replace("]", "");
		}
		String dbType = "";
		{
			dbType = defineStr[1];
		}
		boolean isPk = sql.indexOf("PRIMARY KEY") != -1;
		boolean isAutoIn = sql.indexOf("AUTOINCREMENT") != -1;
		boolean defaultObj = sql.contains("DEFAULT obj");
		
		field.setFieldName(fieldName);
		field.setDbType(dbType);
		field.setPk(isPk);
		field.setDefalutObj(defaultObj);
		field.setAutoIn(isAutoIn);
		
		return field;
	}
	
	private ArrayList<DBClassStructureInfo> buildTableClass(ArrayList<DBTableInfo> dbTabInfoList,String ns){
		
		ArrayList<DBClassStructureInfo> clsList = new ArrayList<>();
		for (DBTableInfo item : dbTabInfoList) {
			DBClassStructureInfo cls = new DBClassStructureInfo();
			
			String clsName = "Tbl"+ StringHelper.upperCaseFirst(item.getTableName());
			cls.setClassName(clsName);
			cls.setTableName(item.getTableName());
			cls.setNameSpace(ns);
			cls.setClassAttriStr(getClsAttriStr(item));
			
			ArrayList<DBClassFieldInfo> fieldList = new ArrayList<>();
			for (DBTableInfo.DBTableFieldInfo dbField : item.getFieldList()) {
				String fieldname = StringHelper.lowerCaseFirst(dbField.getFieldName());
				DBClassFieldInfo field = new DBClassFieldInfo();
				field.setClassName(clsName);
				field.setFieldName(fieldname);
				field.setFieldType(getClsFieldType(dbField));
				field.setFieldAttriStr(getClsFieldAttriStr(dbField));
				field.setDbFieldName(fieldname);
				field.setDbFieldType(dbField.getDbType());
				
				fieldList.add(field);
			}
			cls.setFieldList(fieldList);
			
			clsList.add(cls);
		}
		
		return clsList;
	}
	private String getClsAttriStr(DBTableInfo item){
		return "@DatabaseTable(tableName = \""+item.getTableName()+"\")";
	}
	private String getClsFieldAttriStr(DBTableInfo.DBTableFieldInfo item){
		if(item.isAutoIn()){
			return "@DatabaseField(generatedId = true)";
		}
		if(item.isPk()){
			return "@DatabaseField(id = true)";
		}
		return "@DatabaseField";
	}
	private String getClsFieldType(DBTableInfo.DBTableFieldInfo item){
		String dbtype = item.getDbType().toLowerCase();
		boolean isObj = item.isDefalutObj();

		if(false){}
		else if(dbtype.equals("integer") 
				|| dbtype.equals("int")){
			return isObj?"Integer":"int";
		}
		else if(dbtype.equals("text")){
			return "String";
		}
		else if(dbtype.equals("datetime")){
			return "Date";
		}
		else if(dbtype.equals("real")){
			return isObj?"Float":"float";
		}
		else if(dbtype.equals("long")){
			return isObj?"Long":"long";
		}
		else if(dbtype.equals("blob")){
			return isObj?"Boolean":"boolean";
		}
		else if(dbtype.equals("decimal")){
			return isObj?"Double":"double";
		}
		
		return "String";
	}

	private void saveToDir(String savePath,String fileName,String script,IDbScriptTemplate temp){
//		savePath += mProxyPackageName+"\\";
		File dir = new File(savePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		
		File fp=new File(savePath + StringHelper.upperCaseFirst(fileName) + "." + temp.getFileExName());
		PrintWriter pfp;	
		try {
			pfp = new PrintWriter(fp);
			pfp.print(script);
			pfp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
