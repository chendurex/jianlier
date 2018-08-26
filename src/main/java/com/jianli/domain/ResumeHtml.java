package com.jianli.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@Entity(name = "resume_html")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeHtml {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer resumeId;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "text")
    private String html;

}
