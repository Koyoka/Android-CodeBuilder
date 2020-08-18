package com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator;

import com.eleven.codebuilder.common.PrintHelper;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	
	public static void main(String[] args) {
		PrintHelper.print("printHelper.xxx...1");
		Gson g = new Gson();
//		foo1();
	}
	
//	
	
	private static void foo(){
		
		 Connection connection = null;
		 
		 try {
//			 connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			 connection = DriverManager.getConnection("jdbc:sqlite:C:\\ElevenWorkSpace\\tool\\SqliteDev\\Sample\\demo.db");
			 
			 Statement statement = connection.createStatement();
			 statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			 ResultSet rs = statement.executeQuery("select * from [SQLITE_MASTER]");
			 while(rs.next()){
	  
				 String tabName = rs.getString("name");
				 String tabSql = rs.getString("sql");
				 
				 PrintHelper.print(tabName);
				 PrintHelper.print(tabSql);
//				 String s = ""
//						 + " name:"+ tabName
//						 + " sql:"+ tabSql
////						 + " name:" + rs.getString("name")
////						 + " sql:" + rs.getInt("sql")
////						 + " lastDatelastDate:" + rs.getString("lastDate")
//						 + "";
				
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
	}
}
