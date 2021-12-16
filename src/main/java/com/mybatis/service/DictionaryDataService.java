package com.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis.entity.DictionaryDataEntity;

/**
 * 字典数据
 */
public interface DictionaryDataService extends IService<DictionaryDataEntity> {

    /**
     * 创建
     *
     * @param entity 实体对象
     */
    void create(DictionaryDataEntity entity);

    /**
     * 读取文件创建字典（其他字典）
     *
     * @param filePath 项目文件夹相对路径
     */
    void saveTextDict(String filePath);

    /**
     * 读取文件创建字典（地址/研究领域）
     *
     * @param filePath         资源文件夹相对路径
     * @param description      字典描述
     * @param dictionaryTypeId 字典类型主键UUID
     */
    void saveJsonDict(String filePath, String description, String dictionaryTypeId);

    /**
     * 读取文件创建字典（学校/单位）
     *
     * @param filePath 项目文件夹相对路径
     */
    void saveSchool(String filePath);
}
