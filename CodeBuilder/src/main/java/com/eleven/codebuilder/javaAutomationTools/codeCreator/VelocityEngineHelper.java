package com.eleven.codebuilder.javaAutomationTools.codeCreator;

import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ClassStructureInfo;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.model.ProxyStructureInfo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;

public class VelocityEngineHelper {
    private URL checkRescouce(String tempPath){
        URL fileURL = this.getClass().getResource(tempPath);
        return fileURL;
    }

    private static Template createVelocity(String tempPath){
        File file = new File(tempPath);
        VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        if(file.exists()){
            tempPath = file.getName();
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, file.getParent());
        }else{
            URL fileURL = new VelocityEngineHelper().checkRescouce(tempPath);
            if(fileURL == null){
                return null;
            }
            p.setProperty("file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        }

        ve.init(p);
        Template template = ve.getTemplate(tempPath);
        return template;
    }
    public static String createDtoScript(String tempPath,ClassStructureInfo classInfo){
        Template template =
            createVelocity(tempPath);

        if(template == null){
            return null;
        }

        VelocityContext context = new VelocityContext();
        context.put("clsStruct", classInfo);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
//        System.out.println(writer.toString());
        return writer.toString();

    }

    public static String creteProxyScript(String tempPath, ProxyStructureInfo proxyInfo){
        Template template =
                createVelocity(tempPath);

        if(template == null){
            return null;
        }

        VelocityContext context = new VelocityContext();
        context.put("proxyClass", proxyInfo);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
//        System.out.println(writer.toString());
        return writer.toString();
    }
}
