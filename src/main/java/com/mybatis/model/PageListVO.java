package com.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author James
 * @version V3.1.0
 * @copyright James
 * @date 2021/3/16 8:54
 */
@Data
public class PageListVO<T> {

    @ApiModelProperty(value = "分页信息")
    PaginationVO pagination;
    @ApiModelProperty(value = "对象集合")
    private List<T> list;

}
