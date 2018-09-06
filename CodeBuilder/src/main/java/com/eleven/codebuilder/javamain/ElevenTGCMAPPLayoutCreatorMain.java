package com.eleven.codebuilder.javamain;


import com.eleven.codebuilder.javaAutomationTools.layoutCreator.LayoutXmlHelper;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by eleven on 2016/4/1.
 */
public class ElevenTGCMAPPLayoutCreatorMain {

    public static void main(String[] args) {
        app();
//        smartInput();
    }

    private static void smartInput(){
        String layoutPath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\tgcmapp.smartinput\\src\\main\\res\\layout\\";
        String savePath =
                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\tgcmapp.smartinput\\src\\main\\java\\com\\wuhanins\\smartInput\\appLayout\\";
//                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appLayout\\";

        String nameSpace = "com.wuhanins.smartInput.appLayout";
        String rid = "com.wuhanins.smartInput.R";
        try {
            LayoutXmlHelper.
                    doResolveLayout(
                            "com.wuhanins.smartInput.appLayout.base.INSLayout",
                            nameSpace,rid,
                            savePath,
                            layoutPath,
                            new String[]{
                                    "fragment_camera_signature.xml",
//                                    "activity_test_eleven_recycler_view.xml",
//                                    "list_item_test_eleven_menu_view.xml",
                            });
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void app(){
        String layoutPath =
//                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\res\\layout\\";
                "C:\\ElevenWorkSpace\\company\\ins\\tcgmappSVN\\App\\app\\src\\main\\res\\layout\\";
        String savePath =
//                "C:\\ElevenWorkSpace\\company\\ins\\svn\\Android\\Projects\\ASproject\\CESproject\\TGCMAPP\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appLayout\\";
                "C:\\ElevenWorkSpace\\company\\ins\\tcgmappSVN\\App\\app\\src\\main\\java\\com\\wuhanins\\tgcmapp\\domainService\\appLayout\\";
        String nameSpace = "com.wuhanins.tgcmapp.domainService.appLayout";
        String rid = "com.wuhanins.tgcmapp.R";
        try {
            LayoutXmlHelper.
                    doResolveLayout(nameSpace,rid,
                            savePath,
                            layoutPath,
                            new String[]{
//                                    "view_table_group_item.xml",
//                                    "view_table_multi_edit_item.xml",
//                                    "view_table_sign_edit_item.xml",
//                                    "view_table_group_edit_result_record_item.xml",
//                                    "view_table_group_edit_record_item.xml",
//                                    "view_table_group_edit_value_item.xml",
//
//                                    "view_table_cell_edit_record_item.xml",
//                                    "view_table_cell_edit_result_record_item.xml",
//                                    "view_table_cell_edit_value_item.xml",
//                                    "view_table_cell_item.xml",
                                    //================= smartInput Fragment
                                    "fragment_smartinput_check_item.xml",

//===================================================== 表单viewHolder
                                    "view_new_table_root_desc_norecord_result.xml",
                                    "view_new_table_cell_desc_record_noresult.xml",
                                    "view_new_table_cell_desc_norecord_result.xml",
                                    "view_table_group_item.xml",

                                    "list_smartinput_check_data_select_item_view.xml",
//===================================================== workList viewHolder
                                    "list_work_log_detail_item_view.xml",
                                    "list_work_log_grid_icon_select_item_view.xml",
                                    "list_work_log_select_item_view.xml",
                                    "fragment_summary_detail.xml",

//===================================================== workList viewHolder summary detail
                                    "list_work_log_sum_detail_hr_stream_view.xml",
                                    "list_work_log_sum_detail_eq_stream_view.xml",//
                                    "list_work_log_sum_detail_ws_info_view.xml",//
                                    "list_work_log_sum_detail_ws_info_wp_view.xml",// -
                                    "list_work_log_sum_detail_ps_analyze_view.xml",//
                                    "list_work_log_sum_detail_wq_analyze_view.xml",//
                                    "list_work_log_sum_detail_wc_question_view.xml",//
                                    "list_work_log_sum_detail_wl_change_view.xml",//
                                    "list_work_log_sum_detail_wl_attention_view.xml",//

//===================================================== workList viewHolder authorize
                                    "list_work_log_auth_detail_item_view.xml",
                                    "list_work_log_auth_group_detail_item_view.xml",





//                                    "fragment_smartinput_check_detail.xml",
//                                    "view_table_group_list_record_item.xml",
//                                    "view_table_cell_listitem_record_item.xml",
//                                    "fragment_smartinput_edit_record_list.xml",
//                                    "view_table_xkz_group_other_item.xml",
//                                    "view_table_xkz_cell_checklist_item.xml",
//                                    "view_table_xkz_cell_check_item.xml",
//                                    "view_table_xkz_group_checklist_item.xml",
//                                    "view_table_xkz_cell_checklist.xml",
//                                    "view_table_xkz_cell_edit_item.xml",
//                                    "activity_test_eleven.xml",
//                                    "activity_test_eleven_recycler_view.xml",
//                                    "list_item_test_eleven_menu_view.xml",
                            });
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
