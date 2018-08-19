package com.jianli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "resume")
@JsonIgnoreProperties(value = {"modifyUid", "createUid", "createTime", "modifyTime", "summaryDelete", "expDelete", "eduDelete"})
public class Resume {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer modifyUid;
    @Column(updatable = false)
    private Integer createUid;
    @Column(updatable = false)
    private Timestamp createTime;
    private Timestamp modifyTime;

    private String mobile;
    private String address;
    private String wechat;
    private String email;
    private String name;
    private String objectiveTitle;

    @Column(insertable = false, updatable = false)
    private String summary;
    @Column(insertable = false, updatable = false)
    private String summaryTitle;
    @Column(insertable = false, updatable = false)
    private Integer summarySort;
    @Column(insertable = false, updatable = false)
    private Integer summaryDelete;
    @Column(insertable = false, updatable = false)
    private String expTitle;
    @Column(insertable = false, updatable = false)
    private Integer expSort;
    @Column(insertable = false, updatable = false)
    private Integer expDelete;
    @Column(insertable = false, updatable = false)
    private String eduTitle;
    @Column(insertable = false, updatable = false)
    private Integer eduSort;
    @Column(insertable = false, updatable = false)
    private Integer eduDelete;
    @Column(insertable = false, updatable = false)
    private String skillTitle;
    @Column(insertable = false, updatable = false)
    private Integer skillSort;
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Integer skillDelete;

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
