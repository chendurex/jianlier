package com.jianli.repo;

import com.jianli.domain.WorkExp;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chendurex
 * @date 2018-06-18 14:00
 */
public interface WorkRepo extends CrudRepository<WorkExp, Long> {

}
