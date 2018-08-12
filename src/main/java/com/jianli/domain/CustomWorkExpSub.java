package com.jianli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author chendurex
 * @date 2018-08-04 12:27
 */
@Entity(name = "custom_work_exp_sub")
@Data
@JsonIgnoreProperties(value = {"modifyUid", "createUid", "createTime", "modifyTime", "resumeId", "pid"})
public class CustomWorkExpSub {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer modifyUid;
    @Column(updatable = false)
    private Integer createUid;
    @Column(updatable = false)
    private Timestamp createTime;
    private Timestamp modifyTime;

    @Column(updatable = false)
    private Integer resumeId;
    @Column(updatable = false)
    private Integer pid;
    private String name;
    private String position;
    private String description;
    private String startTime;
    private String endTime;
    private Integer sort;

    public void submit(int uid) {
        this.createTime = Timestamp.from(Instant.now());
        this.modifyTime = createTime;
        this.createUid = uid;
        this.modifyUid = uid;
    }

    public void modify(int uid) {
        this.modifyTime = Timestamp.from(Instant.now());
        this.modifyUid = uid;
    }
}
