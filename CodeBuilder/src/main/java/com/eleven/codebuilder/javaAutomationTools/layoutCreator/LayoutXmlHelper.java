package com.eleven.codebuilder.javaAutomationTools.layoutCreator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by eleven on 2016/4/1.
 */
public class LayoutXmlHelper {

    static String mSavePath = "E:\\javaLayout\\";
    static String mLayoutPath = "E:\\project\\Java\\svn\\YiAnShi\\app\\src\\main\\res\\layout\\";
    static String mNs = "";
    static String rId = "";

    public static void doResolveLayout( String importNS,
                                        String ns,String rid,
                                        String savePath,
                                        String layoutPath,
                                        String[] layoutNames)throws SAXException, IOException{
        domain(importNS, ns, rid,
                savePath,
                layoutPath,
                layoutNames);
    }

    public static void doResolveLayout(
            String ns,String rid,
            String savePath,
            String layoutPath,
            String[] layoutNames) throws SAXException, IOException{
        domain("com.wuhanins.tgcmapp.base.INSLayout", ns, rid,
                 savePath,
                 layoutPath,
                 layoutNames);
    }


    private static String mImportNS = "";
    private static void domain(
            String importNS,
            String ns,String rid,
                        String savePath,
                        String layoutPath,
                        String[] layoutNames) throws SAXException, IOException{
        mImportNS = importNS;

        mSavePath = savePath;
        mLayoutPath = layoutPath;
        mNs = ns;
        rId = rid;


        for(String layoutName : layoutNames){

            LayoutInfo layoutInfo = new LayoutInfo();
            File f =
                    new File(mLayoutPath +layoutName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            if(db == null){
                return;
            }
            Document doc = db.parse(f);
            Element root = doc.getDocumentElement();

            layoutInfo.setLayoutXmlName(f.getName());
            layoutInfo.ViewInfoList = new ArrayList<ViewInfo>();
            parseElement(root,layoutInfo);

            String javaCode = createClassOutput(layoutInfo);
//			ComFn.printStr(javaCode);
            createFile(layoutInfo.LayoutName,javaCode);
//            ComFn.printStr("done " + layoutName);
        }
    }

    private static void createViewInfo(String viewName,NamedNodeMap map,LayoutInfo layoutInfo){
        if(null == map){
            return;
        }

        if(viewName.toLowerCase().equals("fragment")){
            return;
        }

        String viewId = "";
        String viewNS = "";
        boolean needMapping = false;
        for(int i = 0; i < map.getLength(); i++){
            Attr attr = (Attr)map.item(i);
            String attrName = attr.getName();
            String attrValue = attr.getValue();
            if(attrName.toLowerCase().equals("android:id")){
                needMapping = true;
                viewId = attrValue.replace("@+id/", "");
            }
        }

        if(needMapping){
            ViewInfo viewInfo = new ViewInfo();
//            System.out.print(viewName.toLowerCase()+ " " + (viewName.toLowerCase().equals("view")));
            if(viewName.toLowerCase().equals("view")){
                viewNS = "android.view."+viewName;//View
            }else if(viewName.toLowerCase().equals("surfaceview")){
                viewNS = "android.view."+viewName;//SurfaceView
            }else if(viewName.indexOf(".") != -1){
                viewNS = viewName; //user controll
            }else{
                viewNS = "android.widget."+viewName;
            }
            viewInfo.ViewName = viewNS;
            viewInfo.ViewId = viewId;
            layoutInfo.ViewInfoList.add(viewInfo);
//			ComFn.printStr("public " + viewNS + " " + viewId + " = ;");
        }

    }

    private static void parseElement(Element element,LayoutInfo layoutInfo){
        String tagName = element.getNodeName();
        NodeList children = element.getChildNodes();
        NamedNodeMap map = element.getAttributes();
        createViewInfo(tagName,map,layoutInfo);

        for(int i = 0; i < children.getLength(); i++){
            Node node = children.item(i);
            short nodeType = node.getNodeType();
            if(nodeType == Node.ELEMENT_NODE){
                parseElement((Element)node,layoutInfo);
            }
            else if(nodeType == Node.TEXT_NODE){
//					ComFn.printStr(node.getNodeValue());
            }
            else if(nodeType == Node.COMMENT_NODE){
//					ComFn.printStr("<!--");
//					Comment comment = (Comment)node;
//					//注释内容
//					String data = comment.getData();
//					ComFn.printStr(data);
//					ComFn.printStr("-->");
            }
        }
    }

    private static String createClassOutput(LayoutInfo l){
        StringBuilder sb = new StringBuilder();

		/*
		 pCode.Add "package " + viewObj.PackageName + ";"
		pCode.Add ""
		pCode.Add "import java.util.Map;"
		pCode.Add "import android.app.Activity;"
		pCode.Add "import com.yrkj.util.basedao.BaseBean;"
		pCode.Add "import com.yrkj.util.ui.layout.BaseLayout;"
		pCode.Add "import com.yrkj.util.ui.layout.LayoutDataAdapter;"
		pCode.Add "import " + viewObj.ViewFromResour + ";"

pCode.Add ""
		 * */
        appendLine(sb,
                "package "+mNs+";");
        line(sb);
        appendLine(sb,
                "import java.util.Map;");
        appendLine(sb,
                "import android.app.Activity;");
        appendLine(sb,
                "import java.util.Map;");
        line(sb);
        appendLine(sb,
                "import "+mImportNS+";");
        appendLine(sb,
                "import "+rId+";");
        line(sb);


        appendLine(sb,
                "public class " + l.LayoutName + " extends INSLayout{");
        line(sb);
        for(ViewInfo v : l.ViewInfoList){
            appendLine(sb,
                    "    public static final int " + v.ViewId + "Id = R.id." + v.ViewId + ";");
        }
        line(sb);
        for(ViewInfo v : l.ViewInfoList){
            appendLine(sb,
                    "    protected " + v.ViewName + " m" + v.ViewId + ";");
        }
        line(sb);
        appendLine(sb,
                "    protected Activity mCurActy;");
        line(sb);
        appendLine(sb,
                "    public " + l.LayoutName + "(Activity acty){");
        for(ViewInfo v : l.ViewInfoList){
            appendLine(sb,
                    "        m" + v.ViewId + " = (" + v.ViewName + ") acty.findViewById(" + v.ViewId + "Id);");
        }
        appendLine(sb,
                "	}");
        line(sb);
        appendLine(sb,
                "    public " + l.LayoutName + "(android.view.View view){");
        for(ViewInfo v : l.ViewInfoList){
            appendLine(sb,
                    "        m" + v.ViewId + " = (" + v.ViewName + ") view.findViewById(" + v.ViewId + "Id);");
        }
        appendLine(sb,
                "	}");
        line(sb);
        for (ViewInfo v : l.ViewInfoList) {
            line(sb);
            appendLine(sb,
                    "    public " + v.ViewName + " get" + v.getUpViewId() + "() {");

            appendLine(sb,
                    "        return m" + v.ViewId + ";");
            appendLine(sb,
                    "	}");
        }
        line(sb);

        //=========
/*

        appendLine(sb,"    public void bindData(LayoutDataAdapter adp,BaseModelDto data){");
        appendLine(sb,"        if(adp == null || data == null){");
        appendLine(sb,"            return;");
        appendLine(sb,"        }");
        appendLine(sb,"        ");
        appendLine(sb,"        if(adp.BindJoinFiledList != null){");
        appendLine(sb,"            java.util.Iterator iter = adp.BindJoinFiledList.entrySet().iterator();");
        appendLine(sb,"            while (iter.hasNext()) {");
        appendLine(sb,"                Map.Entry entry = (Map.Entry) iter.next();");
        appendLine(sb,"                Object key = entry.getKey();");
        appendLine(sb,"                Object val = entry.getValue();");
        appendLine(sb,"                int viewKey = (Integer) key;");
        appendLine(sb,"                LayoutDataAdapter.JoinData joinData = (LayoutDataAdapter.JoinData) val;");
        appendLine(sb,"                ");
        appendLine(sb,"                switch (viewKey) {");
        for (ViewInfo v : l.ViewInfoList) {
            appendLine(sb, "                case " + v.ViewId + "Id:");
            appendLine(sb, "                    setViewData(adp,get" +v.getUpViewId() + "(),data,joinData.formatString,joinData.data);");
            appendLine(sb, "                    break;");
        }
        appendLine(sb,"                }");
        appendLine(sb,"            }");
        appendLine(sb,"        }");
        appendLine(sb,"        ");
        appendLine(sb,"        if(adp.BindFiledList != null){");
        appendLine(sb,"            java.util.Iterator iter = adp.BindFiledList.entrySet().iterator();");
        appendLine(sb,"            while (iter.hasNext()) {");
        appendLine(sb,"                Map.Entry entry = (Map.Entry) iter.next();");
        appendLine(sb,"                Object key = entry.getKey();");
        appendLine(sb,"                Object val = entry.getValue();");

        appendLine(sb,"                int viewKey = (Integer) key;");
        appendLine(sb,"                String path = (String) val;");
        appendLine(sb,"                switch (viewKey) {");
        for (ViewInfo v : l.ViewInfoList) {
            appendLine(sb, "                case " + v.ViewId + "Id:");
            appendLine(sb, "                    setViewData(adp,get" + v.getUpViewId() + "(),data,\"\",path);");
            appendLine(sb, "                    break;");
        }
        appendLine(sb,"                    ");
        appendLine(sb,"                default:");
        appendLine(sb,"                    break;");
        appendLine(sb,"                }");
        appendLine(sb,"            }");
        appendLine(sb,"         }");

        appendLine(sb,"    }");

*/
        //==============

        line(sb);
        appendLine(sb,
                "}");
        return sb.toString();

    }

    private static void appendLine(StringBuilder sb,String s){
        sb.append(s+"\r\n");
    }

    private static void line(StringBuilder sb){
        sb.append("\r\n");
    }

    public static void createFile(String fileName,String s){
        File fp=new File(mSavePath +fileName+".java");
        String str=s;
        PrintWriter pfp;
        try {
            pfp = new PrintWriter(fp);
            pfp.print(str);
            pfp.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
