package com.jianli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author chendurex
 * @date 2018-06-18 12:26
 */
@Entity(name = "edu_background")
@Data
@JsonIgnoreProperties(value = {"modifyUid", "createUid", "createTime", "modifyTime", "resumeId"})
public class EduBackground {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer modifyUid;
    @Column(updatable = false)
    private Integer createUid;
    @Column(updatable = false)
    private Timestamp createTime;
    private Timestamp modifyTime;
    @Column(updatable = false)
    private Integer resumeId;
    private String college;
    private String professional;
    private String diploma;
    private String startTime;
    private String endTime;
    private String summary;
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
