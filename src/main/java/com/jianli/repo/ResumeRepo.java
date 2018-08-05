package com.jianli.repo;

import com.jianli.domain.Resume;
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
public interface ResumeRepo extends CrudRepository<Resume, Integer> {

    @Modifying
    @Query(value = "update resume set exp_title=?1, exp_sort=?2 WHERE id = ?3", nativeQuery = true)
    void updateExpTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set edu_title=?1,edu_sort=?2 WHERE id = ?3", nativeQuery = true)
    void updateEduTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set skill_title=?1, skill_sort=?2 WHERE id = ?3", nativeQuery = true)
    void updateSkillTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);
}
