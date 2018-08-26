package com.jianli.service.impl;

import com.jianli.repo.ResumeRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.SummaryService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class SummaryServiceImpl implements SummaryService {
    private final ResumeRepo resumeRepo;
    public SummaryServiceImpl(ResumeRepo resumeRepo) {
        this.resumeRepo = resumeRepo;
    }


    @Override
    public ResResult submitResumeSummary(int resumeId, String summary, Integer sort) {
        if (sort == null) {
            resumeRepo.updateSummaryContent(resumeId, summary);
        } else {
            resumeRepo.updateSummaryContentAndSort(resumeId, summary, sort);
        }

        return ResUtils.suc();
    }

    
    @Override
    public ResResult modifyResumeSummary(int id, String summary) {
        resumeRepo.updateSummaryContent(id, summary);
        return ResUtils.suc();
    }

    
    @Override
    public ResResult removeResumeSummary(int id) {
        resumeRepo.removeSummary(id);
        return ResUtils.suc();
    }

    
    @Override
    public ResResult modifyResumeSummaryTitle(int resumeId, int sort, String title) {
        resumeRepo.updateSummaryTitle(title, sort, resumeId);
        return ResUtils.suc();
    }

}
