package com.jianli.repo;

import com.jianli.domain.User;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "update user set access_token=?1, expires_time=?2 WHERE id = ?1", nativeQuery = true)
    void refreshToken(@Param("accessToken")String accessToken, @Param("expiresTime")Integer expiresTime, @Param("id") int id);
}
