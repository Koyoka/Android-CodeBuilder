package com.eleven.codebuilder.javaAutomationTools.codeCreator;

import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.common.StringHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildHelper {

    public static String getClassNameByFileName(String jsonFilePath){
        File jsonFile = new File(jsonFilePath);
        if(!jsonFile.exists()){
            PrintHelper.printErr("no file in:"+jsonFilePath);
            return null;
        }

        String fileName = jsonFile.getName().replace(".json", "");
        PrintHelper.printErr("-----------------");
        PrintHelper.printErr(fileName);
        PrintHelper.printErr("-----------------");
        return fileName;
    }

    public static String readJsonFile(String jsonFilePath){
        File f = new File(jsonFilePath);

        if(!f.exists())
            return null;

        StringBuilder sb = new StringBuilder();
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String str = in.nextLine();
                sb.append(str+"\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    public static boolean buildGetReqCls(ClassStructureInfo cls, String apiUrl){
        String regEx = "\\{[\\s\\S]*?\\}";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat =
                pat.matcher(apiUrl);

        ArrayList<ClassFieldInfo> clsFieldInfos = new ArrayList<>();
        boolean hasGInput = false;
        while(mat.find()){
            String s = mat.group().replace("{","").replace("}","");

            ClassFieldInfo field = new ClassFieldInfo();
            field.setClassName(cls.getClassName());
            field.setFieldType("String");
            field.setFieldName(s);
            clsFieldInfos.add(field);
            hasGInput = true;
        }
        cls.setFieldList(clsFieldInfos);

        return hasGInput;
    }

    public static void saveToDir(String proxyPackageName,String savePath,String fileName,String script){
        savePath += proxyPackageName+"\\";
        File dir = new File(savePath);
        if(!dir.exists()){
            dir.mkdirs();
        }


        File fp=new File(savePath + StringHelper.upperCaseFirst(fileName) + ".java");
        PrintWriter pfp;
        try {
            pfp = new PrintWriter(fp);
            pfp.print(script);
            pfp.close();
            PrintHelper.print("save --- " + savePath+" | " + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
