package com.jianli.repo;

import com.jianli.domain.SkillMaturity;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "delete from skill_maturity WHERE resume_id = ?1", nativeQuery = true)
    void removeSkillByResumeId(@Param("resumeId") int id);

    @Modifying
    @Query(value = "update skill_maturity set sort=?1 WHERE id = ?2", nativeQuery = true)
    void modifySkillSortById(@Param("sort")int sort, @Param("id")int id);
}
