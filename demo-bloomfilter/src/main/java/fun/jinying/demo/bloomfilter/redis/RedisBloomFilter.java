package fun.jinying.demo.bloomfilter.redis;

import io.rebloom.client.Client;

/**
 * @description: redis 实现的布隆过滤器
 * @author: sjy
 * @create: 2019-11-20 19:09
 * <p>
 * 参考文档:https://github.com/RedisBloom/RedisBloom
 * https://github.com/RedisBloom/JRedisBloom
 **/
public class RedisBloomFilter {
    private Client client;
    private String key;

    public void create(String key, double errorRate, long capacity) {
        client.createFilter(key, capacity, errorRate);
        this.key = key;
    }

    public boolean put(String value) {
        return client.add(key, value);
    }

    public boolean mightContain(String value) {
        return client.exists(key, value);
    }

    public RedisBloomFilter(Client client) {
        this.client = client;
    }
}
