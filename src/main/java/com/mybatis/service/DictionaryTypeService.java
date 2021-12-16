package com.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis.entity.DictionaryTypeEntity;

/**
 * 字典分类
 */
public interface DictionaryTypeService extends IService<DictionaryTypeEntity> {

    /**
     * 验证名称
     *
     * @param fullName 名称
     * @param id       主键值
     * @return
     */
    boolean isExistByFullName(String fullName, String id);

    /**
     * 验证编码
     *
     * @param enCode 编码
     * @param id     主键值
     * @return
     */
    boolean isExistByEnCode(String enCode, String id);

    /**
     * 创建
     *
     * @param entity 实体对象
     */
    void create(DictionaryTypeEntity entity);
}
