package fun.jinying;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程内存问题
 * 一个线程内存溢出了，另一个线程并不会死掉
 * 但是因为内存出问题了，触发fullGC，多半其他线程也不会运行的很流畅
 * @author: sjy
 * @create: 2020-03-01 10:16
 **/
public class ThreadMemoryError {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            List<byte[]> bytes = new ArrayList<>(1024);
            for (int i = 0; i < 64; i++) {
                byte[] mb = new byte[1024 * 1024];
                bytes.add(mb);
                if (i > 6) {
                    System.out.println(Thread.currentThread().getName() + i);
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
