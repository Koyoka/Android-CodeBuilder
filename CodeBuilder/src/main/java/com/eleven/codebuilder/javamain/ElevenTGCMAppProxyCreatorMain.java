package com.eleven.codebuilder.javamain;


import com.eleven.codebuilder.common.PrintHelper;
import com.eleven.codebuilder.javaAutomationTools.codeCreator.core.WebApiDataBuilder;

/**
 * Created by �� on 2016/3/30.
 */
public class ElevenTGCMAppProxyCreatorMain {
    public static void main(String[] args) {
        PrintHelper.print("Eleven");
        String savePath =
                "C:\\ElevenWorkSpace\\GitHub\\Android-JPromiser\\HttpVolleyPromiser\\src\\test\\java\\com\\eleven\\httpvolleypromiser\\proxy\\";
//                    "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\build\\libs\\proxy\\";
        String jsonFileDir =
                "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\src\\main\\apiJson\\";
//                    "C:\\ElevenWorkSpace\\GitHub\\Andorid-CodeBuilder\\CodeBuilder\\build\\libs\\";
//            WebApiDataBuilder.newInstance().build(
//                    "com.eleven.httpvolleypromiser.proxy",
//                    "/assets/ProxyTemp.vm",
//                    "/assets/GetInputDtoTemp.vm",
//                    "/assets/ObjectDtoTemp.vm",
//                    savePath,
//                    jsonFileDir + "SysLogin.json",
//                    ""
//            );
        WebApiDataBuilder.newInstance().build(
                "com.eleven.httpvolleypromiser.proxy",
                "/assets/ProxyTemp.vm",
                "/assets/GetInputDtoTemp.vm",
                "/assets/ObjectDtoTemp.vm",
                savePath,
                jsonFileDir
        );
//        fooo_v2();
//        foo_v2();
//        appIml_WebApiProxy();
//        foooo();
//        workflow_v2_callbackBindPromiser();
    }

//    private static int getNfcId(String hexStr){
//
//
//        if(hexStr.length()%2 != 0){
//            return -1;
//        }
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0;i<hexStr.length();i=i+2){
//            sb.insert(0,new char[]{hexStr.charAt(i),hexStr.charAt(i+1)});
//        }
//        System.out.println(sb.toString());
//        int num = Integer.parseInt(sb.toString(), 16);
//        System.out.println(num);
//        return num;
//    }
//
//    public static int byteArrayToInt(byte[] b) {
//        int value= 0;
//        for (int i = 0; i < 4; i++) {
//            int shift= i * 8;
//            value +=(b[i] & 0x000000FF) << shift;
//        }
//        return value;
//    }
//    private static void foooo(){
//
//        /**
//         * -98
//         * -112
//         * 17
//         * 94
//         */
//        byte[] bytes1 = new byte[]{
//                -98,-112,17,94
//        };
//        System.out.println(byteArrayToInt(bytes1));
//        String ret = "";
//        if (bytes1 != null) {
//            for (Byte b : bytes1) {
//                ret += String.format("%02X", b.intValue() & 0xFF);
//            }
//        }
//        System.out.println(ret);
//
//        int targets = (bytes1[0] & 0xff) | ((bytes1[1] << 8) & 0xff00); // |
//
//        String hexStr = "9E90115E";
//        getNfcId("9E90115E");
//        getNfcId("55F8E54C");
//
////        StringBuilder sb = new StringBuilder();
////        for(int i = 0;i<hexStr.length();i=i+2){
////            sb.insert(0,new char[]{hexStr.charAt(i),hexStr.charAt(i+1)});
////        }
////        System.out.println(sb.toString());
////        int num = Integer.parseInt(sb.toString(), 16);
////        System.out.println(num);
////        int i =
////        Integer.parseInt("9E90115E",16);
////        System.out.println(".. "+i);
//
//        byte[] buf = new byte[] { 0x10, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
////        int ret = System.BitConverter.ToInt32(buf, 0);
//
//        int hexLow = 0x9E;
//
//        byte[] bytes = hexStringToByteArray("9E90115E");
//        for (byte b :
//                bytes) {
//            System.out.println(b);
//        }
//
////        String res = null;
////        try {
////            res = new String(bytes,"UTF-8");
////            System.out.println(res);
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
//
//
//
//    }
//    public static byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        try {
//            for (int i = 0; i < len; i += 2) {
//                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                        + Character.digit(s.charAt(i+1), 16));
//            }
//        } catch (Exception e) {
//        }
//        return data;
//    }

//    private static void appIml_WebApiProxy(){
//        String ns =
//                "com.eleven.mavendemoiml.ext.proxy";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\appIml\\src\\main\\java\\com\\eleven\\mavendemoiml\\ext\\proxy\\";
////                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\app\\src\\main\\java\\com\\ins\\lib\\mavendemo\\domain\\proxy\\";
//        String jsonFilePath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\json\\";
//
////                "C:\\ElevenWorkSpace\\company\\ins\\tcgmappSVN\\App\\eleven.tools\\src\\main\\java\\com\\eleven\\jsonfile\\tgcmappWebApi\\";
////                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\eleven.tools\\src\\main\\java\\com\\eleven\\jsonfile\\tgcmappWebApi\\";
//
//
//
////appendLine(sb,"import "+helperRootNS+".common.BaseProxy;");
////appendLine(sb,"import "+helperRootNS+".helper.IHttpHelperListener;");
////appendLine(sb,"import "+helperRootNS+".helper.IOnProxyDoneListener;");
////appendLine(sb,"import "+helperRootNS+".helper.IProxyDataProvider;");
////appendLine(sb,"import "+helperRootNS+".helper.ProviderContext;");
////appendLine(sb,"import "+helperRootNS+".promise.JPromiser;");
//
//        JsonResolveBuilder.getCommonWebApiBuilder().build(ns,saveRootPath,new V1_CommonDtoTemplate(),
//                jsonFilePath + "LoginByPost.json",
//                "");
//    }

//    private static void foo(){
//
//        String ns =
//                "com.wuhanins.core.proxy";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\tgcmapp.core\\src\\main\\java\\com\\wuhanins\\core\\proxy\\";
//        String path =
//                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\eleven.tools\\src\\main\\java\\com\\eleven\\jsonfile\\tgcmapp\\";
//
//        JsonResolveBuilder.getTGCMAppBuilder().build(ns,saveRootPath,new V1_CommonDtoTemplate(),
//                path + "login.json",
//                path + "GetAndroidVersion.json",
//                path + "GetNavigationInfo.json",
//                path + "GetContracts.json",
//                path + "GetCurServerTime.json",
//                path + "GetUsers.json",
//                path + "SaveTableData.json",
//                "");
//    }
//    private static void foo_v2(){
//        String ns =
//                "com.ins.lib.mavendemo.domain.proxy";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\app\\src\\main\\java\\com\\ins\\lib\\mavendemo\\domain\\proxy\\";
//        String path =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\json\\";
//
//        JsonResolveBuilder.getCommonWebApiBuilder_V2().build(ns,saveRootPath,new V1_CommonDtoTemplate(),
//                path + "LoginByPost.json",
//                "");
//    }
//    private static void fooo_v2(){
//        String ns =
//                "com.ins.lib.mavendemo.domain.proxy.v2";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\app\\src\\main\\java\\com\\ins\\lib\\mavendemo\\domain\\proxy\\v2\\";
//        String path =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\json\\";
//
//        JsonResolveBuilder.getCommonWebApiBuilder_V2().build(ns,saveRootPath,new V1_CommonDtoTemplate(),
//                path + "SysLogin.json",
//                "");
//    }

//    private static void foo_v3(){
//        String ns =
//                "com.ins.lib.mavendemo.domain.proxy.v3";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\app\\src\\main\\java\\com\\ins\\lib\\mavendemo\\domain\\proxy\\v3\\";
//        String path =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\json\\";
//
//        JsonResolveBuilder.getCommonWebApiBuilder_V3().build(ns,
//                saveRootPath,
//                new V3_CommonWebApiProxyTemplate(),
//                new V1_CommonDtoTemplate(),
//                path + "LoginByPost.json",
//                path + "SysLogin.json",
//                "");
//    }

//    private static void workflow_v2_callbackBindPromiser(){
//        String ns =
//                "com.wuhanins.workflow";
//        String saveRootPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\InsWorkflow\\src\\main\\java\\com\\wuhanins\\workflow\\";
//        String jsonFilePath =
//                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\json\\";
//
//        JsonResolveBuilder.getCommonWebApiBuilder_V2().build(ns,saveRootPath,new V1_CommonDtoTemplate(),
//                jsonFilePath + "workflow\\DefineApp.json",
//                jsonFilePath + "workflow\\StartWorkflow.json",
//                jsonFilePath + "workflow\\GetCurrentSteps.json",
//                jsonFilePath + "workflow\\ListActions.json",
//                jsonFilePath + "workflow\\DoActionApp.json",
//                jsonFilePath + "workflow\\SetStepOwnersApp.json",
//                jsonFilePath + "workflow\\UserNamesBatch2.json",
//                jsonFilePath + "workflow\\GetBusiObjectEntryId.json",
//                jsonFilePath + "workflow\\GetHistorySetps.json",
//                jsonFilePath + "workflow\\ApplicationCode.json",
//                jsonFilePath + "workflow\\DeleteByBusiId.json",
//                "");
//    }



}
