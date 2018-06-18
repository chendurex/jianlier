package com.jianli.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chendurex
 * @date 2018-06-18 13:45
 */
@Slf4j
public class BeanUtils {
    private static final ObjectWriter OW = new ObjectMapper().writer().withDefaultPrettyPrinter();
    public static String deepPrint(Object o) {
        try {
            return OW.writeValueAsString(o);
        } catch (Exception e) {
            log.error("格式化数据失败，", e);
            throw new RuntimeException(e);
        }
    }
}
