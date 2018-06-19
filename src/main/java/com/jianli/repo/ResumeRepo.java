package com.jianli.repo;

import com.jianli.domain.Resume;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface ResumeRepo extends CrudRepository<Resume, Integer> {
}
