package com.jianli.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * @author chendurex
 * @date 2018-06-18 14:47
 */
@Data
class MetadataInfo {
    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", required = true)
    private Integer uid;
    private Integer modifyUid;
    private Integer createUid;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
