package com.jianli.dto;

import com.jianli.domain.EduBackground;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ApiModel(value = "显示简历信息")
public class ResumeVo extends ResumeParam {

    private EduBackgroundVo eduBackground;
    private SkillMaturityVo skillMaturity;
    private WorkExpVo workExp;

    public void setEduBackground(String title, List<EduBackground> eduBackgrounds) {
        eduBackground = new EduBackgroundVo(title, eduBackgrounds);
    }

    public void setSkillMaturity(String title, List<SkillMaturity> skillMaturities) {
        skillMaturity = new SkillMaturityVo(title, skillMaturities);
    }

    public void setWorkExp(String title, List<WorkExp> workExps) {
        workExp = new WorkExpVo(title, workExps);
    }

    @AllArgsConstructor
    @Getter
    private class EduBackgroundVo {
        private String title;
        private List<EduBackground> eduBackgrounds;

    }

    @AllArgsConstructor
    @Getter
    private class SkillMaturityVo {
        private String title;
        private List<SkillMaturity> skillMaturities;
    }

    @Getter
    @AllArgsConstructor
    private class WorkExpVo {
        private String title;
        private List<WorkExp> workExps;
    }
}
