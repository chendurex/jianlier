package com.jianli.repo;

import com.jianli.domain.Resume;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 15:52
 */
public interface ResumeRepo extends CrudRepository<Resume, Integer> {

    @Modifying
    @Query(value = "update resume set head_img=?1 WHERE id = ?2", nativeQuery = true)
    void updateHeadImg(@Param("headImg")String headImg, @Param("id")int id);

    @Query(value = "select id from resume WHERE create_uid = ?1", nativeQuery = true)
    Integer getResumeIdByUid(@Param("uid") int uid);

    @Modifying
    @Query(value = "update resume set exp_title=?1, exp_sort=?2, exp_delete=0 WHERE id = ?3", nativeQuery = true)
    void updateExpTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set edu_title=?1,edu_sort=?2, edu_delete=0 WHERE id = ?3", nativeQuery = true)
    void updateEduTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set skill_title=?1, skill_sort=?2, skill_delete=0 WHERE id = ?3", nativeQuery = true)
    void updateSkillTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set summary_title=?1, summary_sort=?2, summary_delete=0 WHERE id = ?3", nativeQuery = true)
    void updateSummaryTitle(@Param("title") String title,@Param("sort")int sort, @Param("id") int id);

    @Modifying
    @Query(value = "update resume set summary=?2, summary_delete=0 WHERE id = ?1", nativeQuery = true)
    void updateSummaryContent(@Param("id") int id, @Param("summary") String summary);

    @Modifying
    @Query(value = "update resume set summary=?2, summary_sort=?3 ,summary_delete=0 WHERE id = ?1", nativeQuery = true)
    void updateSummaryContentAndSort(@Param("id") int id, @Param("summary") String summary, @Param("sort") int sort);

    @Modifying
    @Query(value = "update resume set summary_delete=1, summary='', summary_sort=0, summary_title='' WHERE id = ?1", nativeQuery = true)
    void removeSummary(@Param("id") int id);

    @Modifying
    @Query(value = "update resume set exp_delete=1, exp_sort=0, exp_title='' WHERE id = ?1", nativeQuery = true)
    void removeExp(@Param("id") int id);

    @Modifying
    @Query(value = "update resume set skill_delete=1, skill_title='', skill_sort=0 WHERE id = ?1", nativeQuery = true)
    void removeSkill(@Param("id") int id);

    @Modifying
    @Query(value = "update resume set edu_delete=1, edu_title='', edu_sort=0 WHERE id = ?1", nativeQuery = true)
    void removeEdu(@Param("id") int id);
}
