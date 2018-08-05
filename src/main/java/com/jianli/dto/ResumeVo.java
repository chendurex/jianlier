package com.jianli.dto;

import com.jianli.domain.*;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ApiModel(value = "显示简历信息")
public class ResumeVo extends ResumeParam {

    private EduBackgroundVO eduBackground;
    private SkillMaturityVO skillMaturity;
    private WorkExpVO workExp;
    private CustomResumeDescVO customResumeDesc;
    private CustomWorkExpVO customWorkExp;

    public void setEduBackground(String title, Integer sort, List<EduBackground> eduBackgrounds) {
        eduBackground = new EduBackgroundVO(title, sort, eduBackgrounds);
    }

    public void setSkillMaturity(String title, Integer sort, List<SkillMaturity> skillMaturities) {
        skillMaturity = new SkillMaturityVO(title, sort, skillMaturities);
    }

    public void setWorkExp(String title, Integer sort, List<WorkExp> workExps) {
        workExp = new WorkExpVO(title, sort, workExps);
    }

    public void setCustomWorkExp(List<CustomWorkExp> customWorkExps) {
        customWorkExp = new CustomWorkExpVO(customWorkExps);
    }

    public void setCustomResumeDesc(List<CustomResumeDesc> customResumeDescs) {
        customResumeDesc = new CustomResumeDescVO(customResumeDescs);
    }

    @AllArgsConstructor
    @Getter
    private class EduBackgroundVO {
        private String title;
        private Integer sort;
        private List<EduBackground> eduBackgrounds;

    }

    @AllArgsConstructor
    @Getter
    private class SkillMaturityVO {
        private String title;
        private Integer sort;
        private List<SkillMaturity> skillMaturities;
    }

    @Getter
    @AllArgsConstructor
    private class WorkExpVO {
        private String title;
        private Integer sort;
        private List<WorkExp> workExps;
    }

    @Getter
    @AllArgsConstructor
    private class CustomWorkExpVO {
        private List<CustomWorkExp> customWorkExps;
    }

    @Getter
    @AllArgsConstructor
    private class CustomResumeDescVO {
        private List<CustomResumeDesc> customResumeDescs;
    }

}
