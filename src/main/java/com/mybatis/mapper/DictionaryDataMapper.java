package com.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.entity.DictionaryDataEntity;
import org.apache.ibatis.annotations.Select;

/**
 * 字典数据
 */
public interface DictionaryDataMapper extends BaseMapper<DictionaryDataEntity> {

    @Select("INSERT INTO expert_school (F_Id, F_SchoolName, F_SchoolCode) VALUES (#{id}, #{schoolName}, #{schoolCode})")
    void saveSchool(String id, String schoolName, String schoolCode);
}
