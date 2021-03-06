package com.jianli.repo;

import com.jianli.domain.EduBackground;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 14:00
 */
public interface EduBackgroundRepo extends CrudRepository<EduBackground, Integer> {
    @Query(value = "SELECT * FROM edu_background w WHERE w.resume_id = ?1", nativeQuery = true)
    List<EduBackground> listByResumeId(@Param("resumeId") int resumeId);
}
