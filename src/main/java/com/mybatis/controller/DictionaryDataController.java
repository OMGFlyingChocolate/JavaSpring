package com.mybatis.controller;

import com.mybatis.service.DictionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典数据
 */
@RestController
@RequestMapping("/api/dictionaryData")
public class DictionaryDataController {

    /**
     * 字段注入是将Bean作为字段注入到类中，也是最方便，用的最多的注入方式。
     * 字段注入的问题：
     * 1.对象的外部可见性（无法在容器外部实例化TestHandleService，类和容器的耦合度过高，无法脱离容器访问目标对象）
     * 2.可能导致循环依赖（类A注入了类B，类B也注入了类A时创建Bean失败）
     * 3.无法设置注入的对象为final，也无法注入静态变量（原因是变量必须在类实例化进行初始化）
     */
    // @Autowired
    // private DictionaryDataService dictionaryDataService;// 官方不推荐

    /**
     * 构造器注入是通过构造器的方式将Bean注入到字段中
     * 构造器注入解决了：
     * 1.构造器注入能够保证注入的组件不可变，并且确保需要的依赖不为空
     * 2.这样就可以将变量设置为final，并且传递的肯定是一个对象，避免出现空指针异常
     * 3.若是出现字段注入中循环依赖的问题，在项目启动时Spring会非常形象的将错误抛出来
     */
    private final DictionaryDataService dictionaryDataService;// 官方推荐

    @Autowired
    public DictionaryDataController(DictionaryDataService dictionaryDataService) {
        this.dictionaryDataService = dictionaryDataService;
    }

    /**
     * 添加数据字典
     */
    @PostMapping
    public String create() {

        // 地址/省市区
        dictionaryDataService.saveJsonDict("dict/json/Address.json", "通讯地址", "4d659f71faa0418b8d5d2a38f802397b");
        // 研究领域/科目
        dictionaryDataService.saveJsonDict("dict/json/Subject.json", "研究领域", "484af054475411ec915efa163e60171e");
        // 其他字典
        dictionaryDataService.saveTextDict("src/main/resources/dict/text/Dict.txt");
        // 单位/学校
        dictionaryDataService.saveSchool("src/main/resources/dict/text/School.txt");

        return "执行成功";
    }
}
