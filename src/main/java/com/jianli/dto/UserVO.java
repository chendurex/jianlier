package com.jianli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chendurex
 * @date 2018-06-18 13:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Integer id;
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private Integer resumeId;

}
