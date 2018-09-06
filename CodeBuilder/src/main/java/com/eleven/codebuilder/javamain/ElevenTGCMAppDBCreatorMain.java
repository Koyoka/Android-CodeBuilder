package com.eleven.codebuilder.javamain;


import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core.SqliteResolveBuilder;
import com.eleven.codebuilder.javaAutomationTools.sqliteEntityCreator.core.scriptTemplate.OrmDbScriptTemplate;

/**
 * Created by eleven on 2016/4/8.
 */
public class ElevenTGCMAppDBCreatorMain {
    public static void main(String[] args) {
//        tcmapp_base_db();
//        tcmapp_user_project_db();
//        tcmapp_supervisor_log_db();
        insSystemBase();
    }

    private static void demo_db(){
        String dbPath = "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\eleven.tools\\src\\main\\java\\com\\eleven\\dbfile\\tgcmapp\\";
        String savePath = "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\model\\";
        String ns = "com.wuhanins.tgcmapp.domainService.model";
        dbPath = dbPath + "tgcmapp_v_1_20160408.db";

        SqliteResolveBuilder.getInstance().builder(dbPath,savePath,ns,new OrmDbScriptTemplate());
    }

    private static void tcmapp_base_db(){
        String dbPath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\eleven.tools\\src\\main\\java\\com\\eleven\\dbfile\\tgcmappBase\\";
        String savePath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appModel\\db\\tgcmappBase\\";
        String ns = "com.wuhanins.tgcmapp.domainService.appModel.db.tgcmappBase";
//        dbPath = dbPath + "tgcmapp_base_v_0.db";
        dbPath = dbPath + "tgcmapp_base_v_1.db";

        SqliteResolveBuilder.getInstance().builder(dbPath,savePath,ns,new OrmDbScriptTemplate());
    }
    private static void tcmapp_user_project_db(){
        String dbPath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\eleven.tools\\src\\main\\java\\com\\eleven\\dbfile\\tgcmappUserProject\\";
        String savePath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appModel\\db\\tgcmappUserProject\\";
        String ns = "com.wuhanins.tgcmapp.domainService.appModel.db.tgcmappUserProject";
//        dbPath = dbPath + "tgcmapp_user_project_v_0.db";
        dbPath = dbPath + "tgcmapp_user_project_v_1.db";

        SqliteResolveBuilder.getInstance().builder(dbPath,savePath,ns,new OrmDbScriptTemplate());
    }

    private static void tcmapp_supervisor_log_db(){
        String dbPath =
                "C:\\ElevenWorkSpace\\company\\ins\\tcgmappSVN\\App\\eleven.tools\\src\\main\\java\\com\\eleven\\dbfile\\tgcmappSupervisorLog\\";
        String savePath =
                "C:\\ElevenWorkSpace\\company\\ins\\tcgmappSVN\\App\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appModel\\db\\tgcmappSupervisorLog\\";
        String ns = "com.wuhanins.tgcmapp.domainService.appModel.db.tgcmappSupervisorLog";
        dbPath = dbPath + "tgcmapp_supervisor_log_v_1.db";

        SqliteResolveBuilder.getInstance().builder(dbPath,savePath,ns,new OrmDbScriptTemplate());
    }

    private static void insSystemBase(){
        String dbPath =
                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\eleventools\\src\\main\\java\\com\\eleven\\settingfiles\\db\\";
        String savePath =
                "C:\\ElevenWorkSpace\\company\\ins\\project\\insLib\\MavenDemo\\appIml\\src\\main\\java\\com\\eleven\\mavendemoiml\\domainService\\appModel\\dbSystemBase\\";

        String ns = "com.eleven.mavendemoiml.domainService.appModel.dbSystemBase";

        dbPath = dbPath + "ins_system_base_v_1.db";
        SqliteResolveBuilder.getInstance().builder(dbPath,savePath,ns,new OrmDbScriptTemplate());
    }
}
