package com.jianli.education;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author chendurex
 * @description
 * @date 2018-04-18 20:32
 */
@Data
@Entity(name = "edu")
public class EduInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
	// 题目
	private String question;
	// 用户名为
    private String name;
    // 联系号码
    private String contact;
    // 年龄
    private String age;
    //期望院校
    private String school;
    // 期望专业
    private String expMajor;
    // 申请时间
    private String createTime;
    @Override
    public String toString() {
    	return "用户名为："+name+",联系号码为："+contact+ ",题目:"
                +question +",年龄:"+age+
    	",期望院校:"+school+",期望专业:"+expMajor + ",创建时间:"+createTime;
    }
}
