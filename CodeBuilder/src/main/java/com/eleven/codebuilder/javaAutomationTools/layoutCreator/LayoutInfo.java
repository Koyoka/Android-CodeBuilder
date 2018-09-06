package com.eleven.codebuilder.javaAutomationTools.layoutCreator;

import java.util.ArrayList;

/**
 * Created by eleven on 2016/4/1.
 */
public class LayoutInfo {

    public String LayoutName = "";
    public void setLayoutXmlName(String s){

        String defineS = s.replace(".xml", "");
        String[] ss =
                defineS.split("_");

        for(String name : ss){

            LayoutName +=name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        LayoutName =  "Layout" + LayoutName ;

    }

    public ArrayList<ViewInfo> ViewInfoList = null;
}
