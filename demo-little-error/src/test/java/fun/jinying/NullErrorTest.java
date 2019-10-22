package fun.jinying;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class NullErrorTest {
    private final NullError nullError = new NullError();

    @Test
    public void streamsEmpty() {
        NoSuchElementException noSuchElementException = Assertions.assertThrows(NoSuchElementException.class, () -> nullError.StreamsEmpty());
        System.out.println(noSuchElementException);
    }

    @Test
    public void dataEmpty() {
        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class, () -> nullError.dataEmpty());
        System.out.println(nullPointerException);
    }
}