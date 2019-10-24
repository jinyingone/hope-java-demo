package fun.jinying;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

class ConcurrentModifyErrorTest {

    @Test
    void iterateList() {
        ConcurrentModificationException concurrentModificationException = Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            new ConcurrentModifyError().iterateList();
        });

        System.out.println(concurrentModificationException);
    }
}