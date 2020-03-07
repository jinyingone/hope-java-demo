package fun.jinying.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description: json序列化工具
 * @author: sjy
 * @create: 2020-03-01 23:07
 **/
public class JSON {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转为json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转为类
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
