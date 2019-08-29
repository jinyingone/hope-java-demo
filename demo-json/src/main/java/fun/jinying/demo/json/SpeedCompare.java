package fun.jinying.demo.json;

import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * json 速度比较
 *
 * @author jy
 * @date 2019-08-29 下午5:37
 **/
public class SpeedCompare {

    public static void main(String[] args) {
        User user = User.builder()
                .userName("张三")
                .age(10)
                .avatar("http://5b0988e595225.cdn.sohucs.com/images/20190829/df8cdcafb2fb4c6aa3a2641bcf2c08a4.jpeg")
                .login(true)
                .id(100_0000L)
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //预热
            int j = i / 100;
        }
        System.out.println("预热完成");
        System.out.println("tojson gson:" + useJson(user, o -> GsonDemo.toJson(o)));
        System.out.println("tojson fastjson:" + useJson(user, o -> FastjsonDemo.toJson(o)));
        System.out.println("tojson jackson:" + useJson(user, o -> JacksonDemo.toJson(o)));

        String fastJsonStr = FastjsonDemo.toJson(user);
        String gsonStr = GsonDemo.toJson(user);
        String jacksonStr = JacksonDemo.toJson(user);

        System.out.println("fastJsonStr:" + fastJsonStr);
        System.out.println("gsonStr:" + gsonStr);
        System.out.println("jacksonStr:" + jacksonStr);

        System.out.println("fromJson jackson:" + useJson(jacksonStr, User.class, (BiConsumer<String, Class>) (s, aClass) -> JacksonDemo.fromJson(s, aClass)));
        System.out.println("fromJson fastjson:" + useJson(fastJsonStr, User.class, (BiConsumer<String, Class>) (s, aClass) -> FastjsonDemo.fromJson(s, aClass)));
        System.out.println("fromJson gson:" + useJson(gsonStr, User.class, (BiConsumer<String, Class>) (s, aClass) -> GsonDemo.fromJson(s, aClass)));
    }

    public static long useJson(Object obj, Function function) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            function.apply(obj);
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long useJson(String jsonStr, Class clazz, BiConsumer biConsumer) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            biConsumer.accept(jsonStr, clazz);
        }
        return System.currentTimeMillis() - startTime;
    }
}
