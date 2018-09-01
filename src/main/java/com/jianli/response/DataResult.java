package com.jianli.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chendurex
 * @date 2018-09-01 20:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "返回结果")
public class DataResult<T> extends ResResult {
    @ApiModelProperty(value = "返回数据", required = true)
    private T data;
    @JsonIgnore
    private Integer uid;

    DataResult(int status, String desc, T data, Integer uid) {
        super(status, desc);
        this.data = data;
        this.uid = uid;
    }

}
