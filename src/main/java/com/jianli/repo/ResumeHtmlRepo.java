package com.jianli.repo;

import com.jianli.domain.ResumeHtml;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface ResumeHtmlRepo extends CrudRepository<ResumeHtml, Integer> {
    @Query(value = "select id from resume_html WHERE resume_id = ?1", nativeQuery = true)
    Integer getResumeHtmlBy(@Param("resume_id") int resumeId);
}
