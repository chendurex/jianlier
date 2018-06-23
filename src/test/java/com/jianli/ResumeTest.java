package com.jianli;

import com.jianli.commons.BeanUtils;
import com.jianli.controller.ResumeController;
import com.jianli.domain.WorkExp;
import com.jianli.dto.WorkExpInsertParam;
import com.jianli.dto.WorkExpParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
/**
 * @author chendurex
 * @date 2018-06-18 14:15
 */
public class ResumeTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ResumeController resumeController;
    @Test
    public void testWorkExpSubmit() throws Exception {
        MockMvc mockMvc = webAppContextSetup(wac).build();

        WorkExpParam exp = WorkExpInsertParam.builder().corp("吹牛公司").description("吹牛的天线宝宝").position("吹牛总监").build();
        MockHttpServletRequestBuilder builder = post("/resume/queryWorkExp/submit",
                BeanUtils.deepPrint(exp), new BeanPropertyBindingResult(resumeController, ResumeController.class.getSimpleName()));
        builder.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        mockMvc.perform(builder)
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(content().string(Boolean.TRUE.toString())).andReturn();
    }

}
