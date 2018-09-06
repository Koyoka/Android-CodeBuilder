package com.eleven.codebuilder;

import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassFieldInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;

public class TemplateDemo {
    public static void main(String[] args) {

        new TemplateDemo().doit();
    }
    public void doit() {
//        try {
//            new TemplateDemo().getResource();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File("CodeBuilder/src/main/assets/temp.vm");
        System.out.println(file.getAbsolutePath());


        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template template = ve.getTemplate("CodeBuilder/src/main/assets/Temp.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "TTTTT");
        context.put("value", "1");
        ClassStructureInfo obj =
            new ClassStructureInfo();
        obj.setClassName("");
        context.put("classInfo",obj);
//        foo(context);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer.toString());
    }

    private void foo(VelocityContext context){
        ClassStructureInfo proxyClass = new ClassStructureInfo();
        proxyClass.setClassName("+ClassName");
        proxyClass.setNameSpace("+NameSpace");

        ArrayList<ClassFieldInfo> classFieldInfoList = new ArrayList<>();
        {
            ClassFieldInfo classFieldInfo = new ClassFieldInfo();
            classFieldInfo.setFieldName("FiledName");
            classFieldInfo.setFieldType("FileType");
            classFieldInfoList.add(classFieldInfo);
        }
        {
            ClassFieldInfo classFieldInfo = new ClassFieldInfo();
            classFieldInfo.setFieldName("FiledName_1");
            classFieldInfo.setFieldType("FileType_1");
            classFieldInfoList.add(classFieldInfo);
        }
        proxyClass.setFieldList(classFieldInfoList);

        context.put("proxyClass", proxyClass);
    }


    public  void getResource() throws IOException{
        URL fileURL=this.getClass().getResource("");
        System.out.println(fileURL);
    }
//    public static void main(String[] args) throws IOException {
//        Resource res=new Resource();
//        res.getResource();
//    }
}