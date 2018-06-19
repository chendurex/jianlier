package com.jianli.repo;

import com.jianli.domain.WorkExp;
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
}
