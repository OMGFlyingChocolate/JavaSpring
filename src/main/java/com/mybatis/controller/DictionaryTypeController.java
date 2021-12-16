package com.mybatis.controller;

import com.mybatis.entity.DictionaryTypeEntity;
import com.mybatis.model.ActionResult;
import com.mybatis.model.dictionarytype.DictionaryTypeCrForm;
import com.mybatis.service.DictionaryTypeService;
import com.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 字典分类
 */
@RestController
@RequestMapping("/api/system/Base/DictionaryType")
public class DictionaryTypeController {

    private final DictionaryTypeService dictionaryTypeService;

    @Autowired
    public DictionaryTypeController(DictionaryTypeService dictionaryTypeService) {
        this.dictionaryTypeService = dictionaryTypeService;
    }

    /**
     * 添加字典分类
     *
     * @param dictionaryTypeCrForm 实体对象
     * @return 操作结果
     */
    @PostMapping
    public ActionResult create(@RequestBody @Valid DictionaryTypeCrForm dictionaryTypeCrForm) {
        DictionaryTypeEntity entity = JsonUtil.getJsonToBean(dictionaryTypeCrForm, DictionaryTypeEntity.class);
        entity.setParentId(entity.getParentId());
        if (dictionaryTypeService.isExistByFullName(entity.getFullName(), entity.getId())) {
            return ActionResult.fail("名称不能重复");
        }
        if (dictionaryTypeService.isExistByEnCode(entity.getEnCode(), entity.getId())) {
            return ActionResult.fail("编码不能重复");
        }
        dictionaryTypeService.create(entity);
        return ActionResult.success("新建成功");
    }
}
