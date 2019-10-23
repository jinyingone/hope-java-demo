package fun.jinying;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ClassCastErrorTest {

    @Test
    public void jsonCastError() {
        ClassCastException classCastException = Assertions.assertThrows(ClassCastException.class, () -> {
            new ClassCastError().jsonCastError();
        });
        System.out.println(classCastException);
    }
}