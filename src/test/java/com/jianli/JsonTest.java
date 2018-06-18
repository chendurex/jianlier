package com.jianli;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.WorkExp;
import org.junit.Test;

/**
 * @author chendurex
 * @date 2018-06-18 16:09
 */
public class JsonTest {
    @Test
    public void workExpToJson() {
        WorkExp exp = WorkExp.builder().corp("吹牛公司").description("吹牛的天线宝宝").position("吹牛总监").resumeId(4).build();
        System.out.println(BeanUtils.deepPrint(exp));
    }
}
