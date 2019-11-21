package fun.jinying.demo.bloomfilter.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @description: guava的实现
 * @author: sjy
 * @create: 2019-11-19 18:39
 **/
public class GuavaBloomFilter {
    private BloomFilter bloomFilter;

    public BloomFilter create(double errorRate, long capacity) {
        this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), capacity, errorRate);
        return bloomFilter;

    }

    public boolean put(String value) {
        return bloomFilter.put(value);
    }

    public boolean mightContain(String value) {
        return bloomFilter.mightContain(value);
    }
}
