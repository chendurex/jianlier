package com.jianli.repo;

import com.jianli.domain.BinEducation;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface EduRepo extends CrudRepository<BinEducation, Integer> {
}
