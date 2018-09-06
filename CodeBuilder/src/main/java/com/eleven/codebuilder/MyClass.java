package com.eleven.codebuilder;

import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.WebApiDataBuilder;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;

public class MyClass {
    public  void getResource() throws IOException {
        URL fileURL=this.getClass().getResource("/assets/2.txt");
        InputStream is = this.getClass().getResourceAsStream("/assets/Temp.vm");

        if(is == null){
            System.out.println("InputStream is  null");
        }else{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String s="";
            while((s=br.readLine())!=null){
                System.out.println(s);
            }
            System.out.println("InputStream not  null");
        }

        if(fileURL != null) {

            File f = new File(fileURL.getFile());
            System.out.println(fileURL.getFile());
            System.out.println(f.exists() + "eleven");
        }else {
            System.out.println("null1");
        }

        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        System.out.println(path);
        String path1 = new File(this.getClass().getResource("").getFile()).getAbsolutePath();

//        System.out.println(path1);
//        URL fileURL=this.getClass().getResource("assets/temp.vm");


    }
    public void readVm(){

        String tempPath =
//                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\assets\\Temp.vm";
                "/assets/Temp.vm";
        File file = new File(tempPath);
        VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        if(file.exists()){
            tempPath = file.getName();
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, file.getParent());
        }else{
            URL fileURL = this.getClass().getResource(tempPath);
            if(fileURL == null){
                return;
            }
            p.setProperty("file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        }

        ve.init(p);
        Template template = ve.getTemplate(tempPath);
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer.toString());
    }
    public static void main(String[] args) {
        String nameSpace = "com.eleven.httpvolleypromiser.proxy";
        String savePath =
                "C:\\ElevenWorkSpace\\GitHub\\Android-JPromiser\\HttpVolleyPromiser\\src\\test\\java\\com\\eleven\\httpvolleypromiser\\proxy\\";
//                    "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\build\\libs\\proxy\\";
        String jsonFileDir =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\apiJson\\";

        String proxyTemp =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\assets\\ProxyTemp.vm";
        String getDtoTemp =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\assets\\GetInputDtoTemp.vm";
        String objDtoTemp =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\assets\\ObjectDtoTemp.vm";


        if(args != null && args.length == 3){
            nameSpace = args[0];
            savePath = args[1];
            jsonFileDir = args[2];
            create(nameSpace, savePath, jsonFileDir);
        }else{
            PrintHelper.print("-----");
            create(nameSpace, savePath, jsonFileDir);
        }
    }
    private static void create(String ns, String savePath, String jsonFileDir,
                       String proxyTemp, String getDtoTemp, String objDtoTemp){

        WebApiDataBuilder.newInstance().build(
                ns,
                proxyTemp,
                getDtoTemp,
                objDtoTemp,
                savePath+"\\",
                jsonFileDir+"\\"
        );
    }
    private static void create(String ns, String savePath, String jsonFileDir){

        WebApiDataBuilder.newInstance().build(
                ns,
                "/assets/ProxyTemp.vm",
                "/assets/GetInputDtoTemp.vm",
                "/assets/ObjectDtoTemp.vm",
                savePath+"\\",
                jsonFileDir+"\\"
        );
    }

}
