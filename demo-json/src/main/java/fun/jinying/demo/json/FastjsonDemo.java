package fun.jinying.demo.json;

import com.alibaba.fastjson.JSON;

/**
 * fastjson
 * 参考文档:
 * https://github.com/alibaba/fastjson
 * https://github.com/alibaba/fastjson/wiki/Samples-DataBind
 *
 * @author jy
 * @date 2019-08-29 下午5:22
 **/
public class FastjsonDemo {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
