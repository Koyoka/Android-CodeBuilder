package com.eleven.makecodebuilder;

import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.WebApiDataBuilder;


public class MyClass {

    public static void main(String[] args) {

        String nameSpace = "com.eleven.httpvolleypromiser.proxy";
        String savePath =
                "C:\\ElevenWorkSpace\\GitHub\\Android-JPromiser\\HttpVolleyPromiser\\src\\test\\java\\com\\eleven\\httpvolleypromiser\\proxy\\";
        String jsonFileDir =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\MakeCodeBuilder\\src\\main\\java\\com\\eleven\\makecodebuilder\\json\\";
        WebApiDataBuilder.newInstance().build(
                nameSpace,
                savePath+"\\",
                jsonFileDir+"\\"
        );

    }
}
