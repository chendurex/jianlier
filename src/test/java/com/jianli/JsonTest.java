package com.jianli;

import com.jianli.commons.BeanUtils;
import com.jianli.dto.EduBackgroundParam;
import com.jianli.dto.ResumeParam;
import com.jianli.dto.WorkExpParam;
import org.junit.Test;

/**
 * @author chendurex
 * @date 2018-06-18 16:09
 */
public class JsonTest {
    @Test
    public void workExpToJson() {
        WorkExpParam exp = WorkExpParam.builder().corp("1").description("2").position("3").build();

        System.out.println(BeanUtils.deepPrint(exp));
    }

    @Test
    public void eduBackgroundToJson() {
        EduBackgroundParam edu = EduBackgroundParam.builder().college("1").diploma("2").startTime("2017-01-01").endTime("2018-12-12")
                .professional("4").uid(7).build();
        System.out.println(BeanUtils.deepPrint(edu));
    }

    @Test
    public void resumeToJson() {
        ResumeParam edu = ResumeParam.builder().address("1").email("316122221@qq.com").wechat("hahaha")
                .mobile("13975845481").summary("hehe").build();
        System.out.println(BeanUtils.deepPrint(edu));
    }
}
