package com.jianli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chendurex
 * @date 2018-08-11 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WechatLoginParamVO {
    private String appId;
    private String redirectUri;
    private String scope;
    private String state;
}
