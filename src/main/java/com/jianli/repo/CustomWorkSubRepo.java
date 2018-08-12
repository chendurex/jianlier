package com.jianli.repo;

import com.jianli.domain.CustomWorkExpSub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 14:00
 */
public interface CustomWorkSubRepo extends CrudRepository<CustomWorkExpSub, Integer> {
    @Query(value = "SELECT * FROM custom_work_exp_sub w WHERE w.pid = ?1", nativeQuery = true)
    List<CustomWorkExpSub> listBypid(@Param("pid") int pid);
}
