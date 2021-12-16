package com.mybatis.model.dictionarytype;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author James
 * @version V3.1.0
 * @copyright James
 * @date 2021/3/12 15:31
 */
@Data
public class DictionaryTypeCrForm {
    @NotBlank(message = "必填")
    private String parentId;
    @NotBlank(message = "必填")
    private String fullName;
    @NotBlank(message = "必填")
    private String enCode;
    @NotNull(message = "必填")
    private Integer isTree;
    private String description;
    private long sortCode;
}
