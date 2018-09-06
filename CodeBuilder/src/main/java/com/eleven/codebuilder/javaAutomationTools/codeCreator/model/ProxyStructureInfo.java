package com.eleven.codebuilder.javaAutomationTools.codeCreator.model;

public class ProxyStructureInfo {

    private String className;
    private String nameSpace;
    private String apiUrl;
    private String method;
    private String getReqClassName;
    private String postReqClassName;
    private String resClassName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getGetReqClassName() {
        return getReqClassName;
    }

    public void setGetReqClassName(String getReqClassName) {
        this.getReqClassName = getReqClassName;
    }

    public String getPostReqClassName() {
        return postReqClassName;
    }

    public void setPostReqClassName(String postReqClassName) {
        this.postReqClassName = postReqClassName;
    }

    public String getResClassName() {
        return resClassName;
    }

    public void setResClassName(String resClassName) {
        this.resClassName = resClassName;
    }
}
