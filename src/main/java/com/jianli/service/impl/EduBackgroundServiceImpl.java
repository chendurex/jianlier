package com.jianli.service.impl;

import com.jianli.advice.GlobalVariable;
import com.jianli.domain.EduBackground;
import com.jianli.dto.EduBackgroundInsertParam;
import com.jianli.dto.EduBackgroundUpdateParam;
import com.jianli.repo.EduBackgroundRepo;
import com.jianli.repo.ResumeRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.EduBackgroundService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class EduBackgroundServiceImpl implements EduBackgroundService {
    private final EduBackgroundRepo eduBackgroundRepo;
    private final ResumeRepo resumeRepo;
    public EduBackgroundServiceImpl(EduBackgroundRepo eduBackgroundRepo, ResumeRepo resumeRepo) {
        this.eduBackgroundRepo = eduBackgroundRepo;
        this.resumeRepo = resumeRepo;
    }


    @Override
    public ResResult submitEduBackground(EduBackgroundInsertParam param) {
        EduBackground edu = param.toEduBackground();
        edu.submit(GlobalVariable.uid());
        EduBackground saved = eduBackgroundRepo.save(edu);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyEduBackground(EduBackgroundUpdateParam param) {
        EduBackground edu = param.toEduBackground();
        edu.modify(GlobalVariable.uid());
        EduBackground modified = eduBackgroundRepo.save(edu);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    
    @Override
    public ResResult modifyEduBackgroundTitle(int resumeId, int sort, String title) {
        resumeRepo.updateEduTitle(title, sort, resumeId);
        return ResUtils.suc();
    }


    @Override
    public ResResult removeEduBackground(int id) {
        eduBackgroundRepo.deleteById(id);

        return ResUtils.suc();
    }

    
    @Override
    public ResResult removeEduModule(int resumeId) {
        resumeRepo.removeEdu(resumeId);
        eduBackgroundRepo.removeEduByResumeId(resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult queryEduBackground(int id) {
        return ResUtils.data(eduBackgroundRepo.findById(id));
    }


}
