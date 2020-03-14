package fun.jinying.application;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class UserAppServiceTest {

    @Test
    void register() {
    }

    @Test
    void getRegisteredUser() {
    }

    @Test
    void login() {
    }

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