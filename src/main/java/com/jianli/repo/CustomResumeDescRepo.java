package com.jianli.repo;

import com.jianli.domain.CustomResumeDesc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface CustomResumeDescRepo extends CrudRepository<CustomResumeDesc, Integer> {

    @Query(value = "select * from custom_resume_desc WHERE resume_id = ?1", nativeQuery = true)
    List<CustomResumeDesc> listByResumeId(@Param("resumeId") int resumeId);
}
