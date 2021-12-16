package com.sample.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * <p>项目名称: JavaSpring</p>
 * <p>文件名称: GsonJson</p>
 * <p>描述: json解析示例</p>
 * <p>创建时间: 2021/11/18</p>
 *
 * @author: zwh
 * @version: 1.0.0
 */
public class GsonJson {

    public static void main(String[] args) {
        GsonJson gsonJson = new GsonJson();
        gsonJson.codeToName();
    }

    public void codeToName() {
        String str = "[[\"jacket\",\"pants\",\"shorts\"]]";
        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.put("jacket", "夹克衫");
        hashMap.put("pants", "裤子");
        hashMap.put("shorts", "短裤");

        JsonArray jsonArray = JsonParser.parseString(str).getAsJsonArray();
        for (int i = 0, iSize = jsonArray.size(); i < iSize; i++) {
            JsonArray arrayObj = jsonArray.get(i).getAsJsonArray();
            for (int j = 0, jSize = arrayObj.size(); j < jSize; j++) {
                String key = arrayObj.get(j).getAsString();
                arrayObj.set(j, JsonParser.parseString(hashMap.get(key)));
            }
            jsonArray.set(i, arrayObj);
        }
        System.out.println(jsonArray.toString());
    }

    /**
     * 读取json文件
     *
     * @param fileName
     * @return
     */
    public StringBuilder readJsonFile(String fileName) {
        try {
            File jsonFile = new File(fileName);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
