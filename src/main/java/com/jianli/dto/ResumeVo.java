package com.jianli.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@ApiModel(value = "显示简历信息")
@Getter
public class ResumeVo {

    public static UserInfoVO createUserInfoVO(Resume resume) {
        return ResumeVo.UserInfoVO.builder().address(resume.getAddress())
                .email(resume.getEmail()).mobile(resume.getMobile())
                .wechat(resume.getWechat()).name(resume.getName()).objectiveTitle(resume.getObjectiveTitle()).build();
    }

    public static SummaryVO createSummaryVO(Resume resume) {
        return SummaryVO.builder().sort(resume.getSummarySort()).text(resume.getSummary())
                .title(resume.getSummaryTitle()).exist(exist(resume.getSummaryDelete())).build();
    }

    public static EduBackgroundVO createEduBackground(Resume resume, List<EduBackground> eduBackgrounds) {
        return EduBackgroundVO.builder().sort(resume.getEduSort()).title(resume.getEduTitle())
                .exist(exist(resume.getEduDelete())).eduBackgrounds(eduBackgrounds).build();
    }

    public static SkillMaturityVO createSkillMaturity(Resume resume, List<SkillMaturity> skillMaturities) {
        return SkillMaturityVO.builder().sort(resume.getSkillSort()).title(resume.getSkillTitle())
                .exist(exist(resume.getSkillDelete())).skillMaturities(skillMaturities).build();
    }

    public static WorkExpVO createWorkExp(Resume resume, List<WorkExp> workExps) {
        return WorkExpVO.builder().sort(resume.getExpSort()).title(resume.getExpTitle())
                .exist(exist(resume.getExpDelete())).workExps(workExps).build();
    }

    private static boolean exist(Integer integer) {
        return integer != null && integer == 0;
    }


    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Builder
    private static class UserInfoVO {
        private String mobile;
        private String address;
        private String wechat;
        private String email;
        private String name;
        private String objectiveTitle;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Builder
    public static class SummaryVO {
        private String title;
        private String text;
        @JsonIgnore
        private Integer sort;
        @JsonIgnore
        private Boolean exist;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class EduBackgroundVO {
        @JsonIgnore
        private Integer sort;
        @JsonIgnore
        private Boolean exist;
        private String title;
        private List<EduBackground> eduBackgrounds;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class SkillMaturityVO {
        @JsonIgnore
        private Integer sort;
        @JsonIgnore
        private Boolean exist;
        private String title;
        private List<SkillMaturity> skillMaturities;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class WorkExpVO {
        @JsonIgnore
        private Integer sort;
        @JsonIgnore
        private Boolean exist;
        private String title;
        private List<WorkExp> workExps;
    }

}
