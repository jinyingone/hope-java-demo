package fun.jinying;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class StreamErrorTest {

    @Test

    public void streamsEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> StreamError.StreamsEmpty());
    }
}