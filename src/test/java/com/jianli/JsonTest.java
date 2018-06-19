package com.jianli;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.WorkExp;
import org.junit.Test;

/**
 * @author chendurex
 * @date 2018-06-18 16:09
 */
public class JsonTest {
    @Test
    public void workExpToJson() {
        WorkExp exp = WorkExp.builder().corp("1").description("2").position("3").resumeId(4).build();
        System.out.println(BeanUtils.deepPrint(exp));
    }

    @Test
    public void eduBackgroundToJson() {
        EduBackground edu = EduBackground.builder().college("1").diploma("2").startTime("2017-01-01").endTime("2018-12-12")
                .professional("4").resumeId(5).uid(7).build();
        System.out.println(BeanUtils.deepPrint(edu));
    }

    @Test
    public void resumeToJson() {
        Resume edu = Resume.builder().address("1").email("316122221@qq.com").wechat("hahaha")
                .mobile("13975845481").summary("hehe").build();
        System.out.println(BeanUtils.deepPrint(edu));
    }
}
