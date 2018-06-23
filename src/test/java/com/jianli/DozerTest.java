package com.jianli;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.SkillMaturity;
import com.jianli.dto.SkillMaturityInsertParam;
import com.jianli.dto.SkillMaturityParam;
import org.junit.Test;

/**
 * @author chendurex
 * @date 2018-06-23 22:37
 */
public class DozerTest {
    @Test
    public void testDozer() {
        SkillMaturityParam param = SkillMaturityParam.builder().maturity(1).skill("3").uid(4).build();
        System.out.println(BeanUtils.deepPrint(BeanUtils.copy(param, SkillMaturity.class)));
    }
}
