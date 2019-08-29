package fun.jinying.demo.json;

import com.google.gson.Gson;

/**
 * 参考文档:
 * https://github.com/google/gson
 * https://github.com/google/gson/blob/master/UserGuide.md
 *
 * @author jy
 * @date 2019-08-29 下午5:28
 **/
public class GsonDemo {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }
}
