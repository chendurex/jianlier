package com.jianli.dto;

import com.jianli.domain.EduBackground;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "显示简历信息")
public class ResumeVo extends ResumeParam {

    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", required = true)
    private Integer uid;

    private List<EduBackground> eduBackgrounds;
    private List<WorkExp> workExps;
    private List<SkillMaturity> skillMaturities;

}
