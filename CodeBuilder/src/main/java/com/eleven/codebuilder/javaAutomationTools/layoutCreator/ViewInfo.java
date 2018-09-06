package com.eleven.codebuilder.javaAutomationTools.layoutCreator;

/**
 * Created by eleven on 2016/4/1.
 */
public class ViewInfo {
    public String ViewName = "";
    public String ViewId = "";
    public String getUpViewId(){
        String name = ViewId;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;

    }

}
