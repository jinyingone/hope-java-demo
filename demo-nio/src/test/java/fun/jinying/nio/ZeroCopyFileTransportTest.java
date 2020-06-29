package fun.jinying.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author jy
 * @date 2020/4/28
 */
public class ZeroCopyFileTransportTest {

    @Test
   public void copy() throws IOException {
        ZeroCopyFileTransport zeroCopyFileTransport = new ZeroCopyFileTransport();
        zeroCopyFileTransport.copy(new File("D:\\idea\\hope-java-demo\\demo-nio\\src\\test\\java\\fun\\jinying\\nio\\ZeroCopyFileTransportTest.java"),
                new File("D:\\ZeroCopyFileTransportTest.java"));
    }
}