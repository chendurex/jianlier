package com.jianli.dto;

import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@ApiModel(value = "显示简历信息")
@Getter
public class ResumeVo {
    private Integer id;
    private UserInfoVO userInfoVO;
    private SummaryVO summaryVO;
    private EduBackgroundVO eduBackground;
    private SkillMaturityVO skillMaturity;
    private WorkExpVO workExp;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserInfoVO(Resume resume) {
        userInfoVO = new UserInfoVO(resume.getAddress(), resume.getEmail(), resume.getMobile(),
                resume.getName(), resume.getWechat());
    }

    public void setSummary(String title, String text) {
        this.summaryVO = new SummaryVO(title, text);
    }

    public void setEduBackground(String title, List<EduBackground> eduBackgrounds) {
        eduBackground = new EduBackgroundVO(title, eduBackgrounds);
    }

    public void setSkillMaturity(String title, List<SkillMaturity> skillMaturities) {
        skillMaturity = new SkillMaturityVO(title, skillMaturities);
    }

    public void setWorkExp(String title, List<WorkExp> workExps) {
        workExp = new WorkExpVO(title, workExps);
    }


    @AllArgsConstructor
    @Getter
    public static class UserInfoVO {
        private String mobile;
        private String address;
        private String wechat;
        private String email;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public static class SummaryVO {
        private String title;
        private String text;
    }

    @AllArgsConstructor
    @Getter
    public static class EduBackgroundVO {
        private String title;
        private List<EduBackground> eduBackgrounds;

    }

    @AllArgsConstructor
    @Getter
    public static class SkillMaturityVO {
        private String title;
        private List<SkillMaturity> skillMaturities;
    }

    @Getter
    @AllArgsConstructor
    public static class WorkExpVO {
        private String title;
        private List<WorkExp> workExps;
    }

}
