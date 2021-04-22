package com.example.lib.lang;

import java.io.File;
import java.util.ArrayList;

public class StringsXmlUtil {
    private ArrayList<String> keyList = new ArrayList<>();
    private ArrayList<String> valueList = new ArrayList<>();
    //private LinkedHashMap<String,String> stringXmlMap = new LinkedHashMap();

    public void parse(){
        String key = getFileContent("/Users/lizhijiang/AndroidStudioProjects/JavaAppTest/app/src/main/assets/strings_language_key.txt");
        putKeyToList(key);
        String value = getFileContent("/Users/lizhijiang/AndroidStudioProjects/JavaAppTest/app/src/main/assets/strings_language_value_in.txt");
        putValueToList(value);

        toStringsXml();
    }

    private String getFileContent(String assetFileName){

        if(TextUtils.isEmpty(assetFileName)){
            return "";
        }else{
            return ResourceUtils.readAssets2String(assetFileName);
        }
    }

    private void putKeyToList(String s){
        if(TextUtils.isEmpty(s))return;
        keyList.clear();
        String[] keys = s.split("\n");
        for (int i = 0; i < keys.length; i++) {
            //Log.d("mtest","keys["+i+"]: "+keys[i]);
            keyList.add(keys[i]);
        }
    }

    private void putValueToList(String s){
        if(TextUtils.isEmpty(s))return;
        valueList.clear();
        String[] values = s.split("\n");
        for (int i = 0; i < values.length; i++) {
            //Log.d("mtest","values["+i+"]: "+values[i] + " length:"+ values[i].length());
            String value = values[i];


            //删除value最后的换行符
            if(value.endsWith("\n") && value.indexOf("\n") != -1){
                int pos = value.indexOf("\n");
                value = value.substring(0,pos);
            }else if(value.endsWith("\r\n") && value.indexOf("\r\n") != -1){
                int pos = value.indexOf("\r\n");
                value = value.substring(0,pos);
            }else if(value.endsWith("\r") && value.indexOf("\r") != -1){
                int pos = value.indexOf("\r");
                value = value.substring(0,pos);
            }
            if(value.equals(" ") || value.equals(' ') || (value.length() == 1 && value.charAt(0) == 160)){
                value = "";
            }

/*            //找出value为空时为什么写入文件后对应的位置会出现空格
            if(value.length() <= 2){
                Log.d("mtest","value.length() ："+value.length());
                for (int j = 0; j < value.length(); j++) {
                    char cj = value.charAt(j);
                    Log.d("mtest","c"+j+" int : "+(int)cj);
                }
            }*/

            valueList.add(value);
        }
    }

    private void toStringsXml(){
        if(keyList.size() != valueList.size()){
            //Log.e("mtest","keyList size != valueList size");
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        stringBuffer.append("\n");
        stringBuffer.append("<resources xmlns:xliff=\"urn:oasis:names:tc:xliff:document:1.2\">");
        stringBuffer.append("\n");

        for (int i = 0; i < keyList.size(); i++) {
            if(keyList.get(i).trim().isEmpty()){
                stringBuffer.append("\n");
                continue;
            }
            String line = "";
            if(keyList.get(i).contains("array") && valueList.get(i).contains("<item>")){
                String[] array = keyList.get(i).split(" ");
                if(array != null && array.length > 0){
                    stringBuffer.append("    <string-array name=\""+array[0]+"\">\n");
                    //item
                    String[] arrayItem = valueList.get(i).split("</item>");
                    if(arrayItem != null && arrayItem.length > 0){
                        for (int j = 0; j < arrayItem.length; j++) {
                            stringBuffer.append("        "+arrayItem[j].trim()
                                    .replace(" & # 160;","&#160;")
                                    .replace("\\ ","\\")
                                    .replace("& amp;","&amp;")
                                    .replace("<xliff: g","<xliff:g")
                                    .replace("<XLIFF: G","<xliff:g")
                                    .replace("</ xliff: g>","</xliff:g>")
                                    .replace("\'","")
                                    .replace("\'\'","")
                                    +"</item>\n");
                        }
                    }
                    stringBuffer.append("    </string-array>");
                    stringBuffer.append("\n");
                    continue;
                }

            }else if(keyList.get(i).contains("translatable=\"false\"")){
                String[] array = keyList.get(i).split(" ");
                if(array != null && array.length > 0){
                    line = "    <string name=\""+array[0]+"\"" +" translatable=\"false\""+">"+valueList.get(i)
                            .replace(" & # 160;","&#160;")
                            .replace("\\ ","\\")
                            .replace("& amp;","&amp;")
                            .replace("<xliff: g","<xliff:g")
                            .replace("<XLIFF: G","<xliff:g")
                            .replace("</ xliff: g>","</xliff:g>")
                            .replace("\'","")
                            .replace("\'\'","")
                            +"</string>";
                }
            }else{
                line = "    <string name=\""+keyList.get(i)+"\">"+valueList.get(i)
                        .replace(" & # 160","&#160;")
                        .replace("\\ ","\\")
                        .replace("& amp;","&amp;")
                        .replace("<xliff: g","<xliff:g")
                        .replace("<XLIFF: G","<xliff:g")
                        .replace("</ xliff: g>","</xliff:g>")
                        .replace("\'","")
                        .replace("\'\'","")
                        +"</string>";
            }
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }

        stringBuffer.append("</resources>");

        //写入文件
        File file = new File("/Users/lizhijiang/Desktop/","strings.xml");
        FileUtil.writeSDFile(file,stringBuffer.toString());
    }
}
