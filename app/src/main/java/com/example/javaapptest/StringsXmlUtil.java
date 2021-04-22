package com.example.javaapptest;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class StringsXmlUtil {
    private ArrayList<String> keyList = new ArrayList<>();
    private ArrayList<String> valueList = new ArrayList<>();
    //private LinkedHashMap<String,String> stringXmlMap = new LinkedHashMap();

    public void parse(Context context){
        String key = getFileContent(context,"strings_language_key.txt");
        putKeyToList(key);
        String value = getFileContent(context,"strings_language_value.txt");
        putValueToList(value);

        toStringsXml();
    }

    private String getFileContent(Context context,String assetFileName){

        if(TextUtils.isEmpty(assetFileName)){
            return "";
        }else{
            return ResourceUtils.readAssets2String(context,assetFileName);
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
            Log.e("mtest","keyList size != valueList size");
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        stringBuffer.append("\n");
        stringBuffer.append("<resources>");
        stringBuffer.append("\n");

        for (int i = 0; i < keyList.size(); i++) {
            String line = "    <string name=\""+keyList.get(i)+"\">"+valueList.get(i)+"</string>";
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }

        stringBuffer.append("</resources>");

        //写入文件
        File file = new File("/sdcard","strings.xml");
        FileUtil.writeSDFile(file,stringBuffer.toString());
    }

}
