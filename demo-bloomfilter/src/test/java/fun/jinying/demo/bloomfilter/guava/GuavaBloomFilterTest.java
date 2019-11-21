package fun.jinying.demo.bloomfilter.guava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

class GuavaBloomFilterTest {
    static GuavaBloomFilter bloomFilter;

    @BeforeAll
    static void create() {
        GuavaBloomFilter bloomFilter = new GuavaBloomFilter();
        bloomFilter.create(0.001, 100);
        GuavaBloomFilterTest.bloomFilter = bloomFilter;
    }

    @org.junit.jupiter.api.Test
    void put() {
        for (int i = 0; i < 50; i++) {
            bloomFilter.put(Integer.toString(i));
        }
    }

    @org.junit.jupiter.api.Test
    void mightContain() {
        put();
        for (int i = 0; i < 50; i++) {
            boolean b = bloomFilter.mightContain(Integer.toString(i));
            Assertions.assertTrue(b);
        }

        for (int i = 50; i < 100; i++) {
            boolean b = bloomFilter.mightContain(Integer.toString(i));
            System.out.println(i);
            Assertions.assertFalse(b);
        }
    }
}