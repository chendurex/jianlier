package com.jianli.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chendurex
 * @date 2018-06-18 13:45
 */
@Slf4j
public class BeanUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectWriter OW = OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
    private static final Mapper MAPPER =  DozerBeanMapperBuilder.buildDefault();
    public static String deepPrint(Object o) {
        try {
            return OW.writeValueAsString(o);
        } catch (Exception e) {
            log.error("格式化数据失败，", e);
            throw new RuntimeException(e);
        }
    }

    public static <T>T copy(Object sources, Class<T> desc) {
        return MAPPER.map(sources, desc);
    }


    public static <T>T toJavaObject(InputStream is, Class<T> clz) {
        try {
            return OBJECT_MAPPER.readValue(is, clz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
