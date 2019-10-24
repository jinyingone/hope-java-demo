package fun.jinying;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class TimeOutErrorTest {

    @Test
    void requestNetResource() {
        TimeoutException timeoutException = Assertions.assertThrows(TimeoutException.class, () -> {
            new TimeOutError().requestNetResource();
        });
        System.out.println(timeoutException);
    }
}