package com.jianli.repo;

import com.jianli.domain.ResumeModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface ResumeModuleRepo extends CrudRepository<ResumeModule, Integer> {

    @Query(value = "select * from resume_module WHERE uid = ?1", nativeQuery = true)
    List<ResumeModule> queryByUid(@Param("uid") int uid);
}
