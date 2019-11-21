package fun.jinying.demo.bloomfilter.redis;

import io.rebloom.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RedisBloomFilterTest {
    private static RedisBloomFilter bloomFilter;

    @BeforeAll
    static void create() {
        Client client = new Client("10.16.70.244", 6379);
        client.delete("testBF");
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter(client);
        redisBloomFilter.create("testBF", 0.01, 100);
        RedisBloomFilterTest.bloomFilter = redisBloomFilter;
    }

    @Test
    void put() {
        for (int i = 0; i < 50; i++) {
            bloomFilter.put(Integer.toString(i));
        }
    }

    @Test
    void mightContain() {
        put();
        for (int i = 0; i < 50; i++) {
            boolean b = bloomFilter.mightContain(Integer.toString(i));
            Assertions.assertTrue(b);
        }

        for (int i = 50; i < 100; i++) {
            boolean b = bloomFilter.mightContain(Integer.toString(i));
            System.out.println(i + ":" + b);
            Assertions.assertFalse(b);
        }
    }
}