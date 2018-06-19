package com.jianli.repo;

import com.jianli.domain.SkillMaturity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface SkillRepo extends CrudRepository<SkillMaturity, Integer> {
    @Query(value = "SELECT * FROM skill_maturity w WHERE w.resume_id = ?1", nativeQuery = true)
    List<SkillMaturity> listByResumeId(@Param("resumeId") int resumeId);
}
