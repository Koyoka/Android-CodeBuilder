package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.model;

import java.util.ArrayList;

public class DBTableInfo {
	private String tableName;
	private ArrayList<DBTableFieldInfo> fieldList;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<DBTableFieldInfo> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList<DBTableFieldInfo> fieldList) {
		this.fieldList = fieldList;
	}

	public static class DBTableFieldInfo{
		private String fieldName;
		private String dbType;
		private boolean isPk;
		private boolean isAutoIn;
		private boolean defalutObj;
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
		}
		public boolean isPk() {
			return isPk;
		}
		public void setPk(boolean isPk) {
			this.isPk = isPk;
		}
		public boolean isAutoIn() {
			return isAutoIn;
		}
		public void setAutoIn(boolean isAutoIn) {
			this.isAutoIn = isAutoIn;
		}

		public boolean isDefalutObj() {
			return defalutObj;
		}

		public void setDefalutObj(boolean defalutObj) {
			this.defalutObj = defalutObj;
		}
	}

}
