package com.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author James
 * @version V3.1.0
 * @copyright James
 * @date 2021/3/16 8:54
 */
@Data
public class PaginationVO {

    @ApiModelProperty(value = "当前页数", example = "1")
    private Long currentPage;

    @ApiModelProperty(value = "每页条数", example = "20")
    private Long pageSize;

    @ApiModelProperty(value = "总数据条数", example = "1000")
    private Integer total;
}
