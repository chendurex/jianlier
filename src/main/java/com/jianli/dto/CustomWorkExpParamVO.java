package com.jianli.dto;

import com.jianli.domain.CustomWorkExpSub;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-08-04 12:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomWorkExpParamVO {
    private Integer id;
    private String title;
    private List<CustomWorkExpSub> customWorkExpSubs;
}