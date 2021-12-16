package com.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis.entity.DictionaryTypeEntity;
import com.mybatis.mapper.DictionaryTypeMapper;
import com.mybatis.service.DictionaryTypeService;
import com.util.RandomUtil;
import com.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 字典分类
 */
@Service
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeMapper, DictionaryTypeEntity> implements DictionaryTypeService {

    @Override
    public boolean isExistByFullName(String fullName, String id) {
        QueryWrapper<DictionaryTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictionaryTypeEntity::getFullName, fullName);
        if (!StringUtil.isEmpty(id)) {
            queryWrapper.lambda().ne(DictionaryTypeEntity::getId, id);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean isExistByEnCode(String enCode, String id) {
        QueryWrapper<DictionaryTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictionaryTypeEntity::getEnCode, enCode);
        if (!StringUtil.isEmpty(id)) {
            queryWrapper.lambda().ne(DictionaryTypeEntity::getId, id);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public void create(DictionaryTypeEntity entity) {
        entity.setId(RandomUtil.uuId());
        entity.setCreatorUserId("admin");
        this.save(entity);
    }
}
