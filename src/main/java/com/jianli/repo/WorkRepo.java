package com.jianli.repo;

import com.jianli.domain.WorkExp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 14:00
 */
public interface WorkRepo extends CrudRepository<WorkExp, Integer> {
    @Query(value = "SELECT * FROM work_exp w WHERE w.resume_id = ?1", nativeQuery = true)
    List<WorkExp> listByResumeId(@Param("resumeId") int resumeId);

    @Modifying
    @Query(value = "delete from work_exp WHERE resume_id = ?1", nativeQuery = true)
    void removeExpByResumeId(@Param("resumeId") int id);

    @Modifying
    @Query(value = "update work_exp set sort=?1 WHERE id = ?2", nativeQuery = true)
    void modifyExpSortById(@Param("sort")int sort, @Param("id")int id);
}
