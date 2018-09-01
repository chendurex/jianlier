package com.jianli.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chendurex
 * @description
 * @date 2018-03-18 12:33
 */
@Data
public class ResResult {
    @ApiModelProperty(value = "返回状态(1成功，-1失败)", required = true)
    private final int status;
    @ApiModelProperty(value = "返回说明，如果失败，则会有说明信息", required = true)
    private final String desc;
    public ResResult(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
