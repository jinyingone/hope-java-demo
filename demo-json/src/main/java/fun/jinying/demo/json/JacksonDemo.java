package fun.jinying.demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * jackson
 * 参考文档:
 *
 * @author jy
 * @date 2019-08-29 下午5:18
 **/
public class JacksonDemo {
    // create once, reuse
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        try {
            return mapper.readValue(text, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
