package com.jianli.education;

import lombok.Data;

/**
 * @author chendurex
 * @description
 * @date 2018-04-18 20:32
 */
@Data
public class EduInfo {
	// 题目
	private String question;
	// 用户名为
    private String name;
    // 联系号码
    private String contact;
    // 报考原因
    private String whyEdu;
    // 报考方式
    private String tendEdu;
    // 年龄
    private String age;
    // 目前学历
    private String major;
    // 拿证时长
    private String acceptableEdu;
    //期望院校
    private String school;
    // 期望专业
    private String expMajor;

    @Override
    public String toString() {
    	return "用户名为："+name+",联系号码为："+contact+",报考原因:"+whyEdu+
    	",报考方式："+tendEdu + ",题目:"+question +",年龄:"+age+",目前学历:"+
    	major+",拿证时长:"+acceptableEdu+",期望院校:"+school+",期望专业:"+expMajor;
    }
}
