package com.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mybatis.entity.DictionaryDataEntity;
import com.mybatis.mapper.DictionaryDataMapper;
import com.mybatis.service.DictionaryDataService;
import com.sample.json.GsonJson;
import com.util.PinYinUtil;
import com.util.RandomUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Objects;

/**
 * 字典数据
 */
@Service
public class DictionaryDataServiceImpl extends ServiceImpl<DictionaryDataMapper, DictionaryDataEntity> implements DictionaryDataService {

    @Override
    public void create(DictionaryDataEntity entity) {
        entity.setId(RandomUtil.uuId());
        entity.setSimpleSpelling(Objects.requireNonNull(PinYinUtil.getFirstSpell(entity.getFullName())).toUpperCase());
        entity.setCreatorUserId("admin");
        entity.setEnabledMark(1);// 有效标志
        this.save(entity);
    }

    @Override
    public void saveTextDict(String filePath) {
        Date date = new Date();
        String description = "";
        String dictionaryTypeId = "";
        FileInputStream inputStream;
        BufferedReader bufferedReader;
        try {
            inputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.length() > 0) {
                    String[] s = str.split("\\|");
                    String name = s[0];
                    if (name.equals("description")) {
                        description = s[1];
                    } else if (name.equals("dictionaryTypeId")) {
                        dictionaryTypeId = s[1];
                    } else {
                        DictionaryDataEntity entity = new DictionaryDataEntity();
                        entity.setParentId("0");
                        entity.setFullName(name);
                        entity.setEnCode(s[1]);
                        entity.setSortCode(0L);// 排序
                        entity.setCreatorTime(date);// 创建时间
                        entity.setDescription(description);
                        entity.setDictionaryTypeId(dictionaryTypeId);
                        this.create(entity);
                    }
                }
                System.out.println(str);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveJsonDict(String filePath, String description, String dictionaryTypeId) {
        String path = Objects.requireNonNull(GsonJson.class.getClassLoader().getResource(filePath)).getPath();
        GsonJson gsonJson = new GsonJson();
        StringBuilder sb = gsonJson.readJsonFile(path);
        JsonArray jsonArrayProvince = JsonParser.parseString(sb.toString()).getAsJsonArray();
        JsonElement jsonElementCity;
        JsonArray jsonArrayCity;
        JsonElement jsonElementArea;
        JsonArray jsonArrayArea;
        DictionaryDataEntity entity;
        Date date = new Date();
        for (int i = 0, provinceSize = jsonArrayProvince.size(); i < provinceSize; i++) {
            JsonObject jsonObjectProvince = jsonArrayProvince.get(i).getAsJsonObject();
            String provinceName = jsonObjectProvince.get("name").getAsString();

            System.out.println(provinceName);
            String provinceUUID = RandomUtil.uuId();
            entity = new DictionaryDataEntity();
            entity.setId(provinceUUID);
            entity.setParentId("0");
            saveDictionaryDataEntity(description, dictionaryTypeId, entity, date, provinceName);

            jsonElementCity = jsonObjectProvince.get("sub");
            if (jsonElementCity.isJsonArray()) {
                jsonArrayCity = jsonElementCity.getAsJsonArray();
                for (int j = 0, citySize = jsonArrayCity.size(); j < citySize; j++) {
                    JsonObject jsonObjectCity = jsonArrayCity.get(j).getAsJsonObject();
                    String cityName = jsonObjectCity.get("name").getAsString();

                    System.out.println("	" + cityName);
                    String cityUUID = RandomUtil.uuId();
                    entity = new DictionaryDataEntity();
                    entity.setId(cityUUID);
                    entity.setParentId(provinceUUID);
                    saveDictionaryDataEntity(description, dictionaryTypeId, entity, date, cityName);

                    jsonObjectCity.get("sub");
                    jsonElementArea = jsonObjectCity.get("sub");
                    if (jsonElementArea.isJsonArray()) {
                        jsonArrayArea = jsonElementArea.getAsJsonArray();
                        for (int k = 0, areaSize = jsonArrayArea.size(); k < areaSize; k++) {
                            JsonObject jsonObjectArea = jsonArrayArea.get(k).getAsJsonObject();
                            String areaName = jsonObjectArea.get("name").getAsString();

                            System.out.println("		" + areaName);
                            String areaUUID = RandomUtil.uuId();
                            entity = new DictionaryDataEntity();
                            entity.setId(areaUUID);
                            entity.setParentId(cityUUID);
                            saveDictionaryDataEntity(description, dictionaryTypeId, entity, date, areaName);
                        }
                    }
                }
            }
        }
    }

    private void saveDictionaryDataEntity(String description, String dictionaryTypeId, DictionaryDataEntity entity, Date date, String provinceName) {
        entity.setFullName(provinceName);
        entity.setEnCode(provinceName);
        entity.setSortCode(0L);// 排序
        entity.setSimpleSpelling(Objects.requireNonNull(PinYinUtil.getFirstSpell(entity.getFullName())).toUpperCase());
        entity.setCreatorTime(date);// 创建时间
        entity.setEnabledMark(1);// 有效标志
        entity.setDescription(description);
        entity.setCreatorUserId("admin");
        entity.setDictionaryTypeId(dictionaryTypeId);
        this.save(entity);
    }

    @Override
    public void saveSchool(String filePath) {
        FileInputStream inputStream;
        BufferedReader bufferedReader;
        try {
            inputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String schoolName;
            while ((schoolName = bufferedReader.readLine()) != null) {
                if (schoolName.length() > 0) {
                    baseMapper.saveSchool(
                            RandomUtil.uuId(),
                            schoolName,
                            Objects.requireNonNull(PinYinUtil.getFirstSpell(schoolName)).toUpperCase()
                    );
                    System.out.println(schoolName);
                }
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
