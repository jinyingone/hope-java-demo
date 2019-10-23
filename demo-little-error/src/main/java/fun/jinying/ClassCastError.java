package fun.jinying;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * 类型转换错误
 *
 * @author jy
 * @date 2019-10-23 上午11:08
 **/
public class ClassCastError {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 由于json序列化,经过网络传输,再反序列化,发下格式和约定的不一致
     */
    public void jsonCastError() throws IOException {
        String msg = sendMsg();
        //网络传输
        consumeMsg(msg);
    }

    /**
     * 模拟发送消息
     *
     * @return
     * @throws JsonProcessingException
     */
    private String sendMsg() throws JsonProcessingException {
        HashMap<String, Object> msgMap = new HashMap<>(2);
        msgMap.put("type", 1);
        //正常数据应该是map  Collections.singletonMap("key", "value"), 但是却被多序列化字符串一次
        msgMap.put("data", objectMapper.writeValueAsString(Collections.singletonMap("key", "value")));
        return objectMapper.writeValueAsString(msgMap);
    }

    /**
     * 模拟消费消息
     *
     * @param msg
     * @throws IOException
     */
    private void consumeMsg(String msg) throws IOException {
        HashMap map = objectMapper.readValue(msg, HashMap.class);
        HashMap<String, Object> data = (HashMap) map.get("data");
        System.out.println(data);
    }
}
