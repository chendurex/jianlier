package com.jianli.repo;

import com.jianli.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author chendurex
 * @date 2018-06-18 14:00
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM user w WHERE w.openid = ?1", nativeQuery = true)
    User get(@Param("openid") String openid);
}
