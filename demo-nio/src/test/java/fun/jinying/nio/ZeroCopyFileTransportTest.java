package fun.jinying.nio;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jy
 * @date 2020/4/28
 */
class ZeroCopyFileTransportTest {

    @org.junit.jupiter.api.Test
    void copy() throws IOException {
        ZeroCopyFileTransport zeroCopyFileTransport = new ZeroCopyFileTransport();
        zeroCopyFileTransport.copy(new File("/Users/jy/IdeaProjects/hope-java-demo/demo-nio/src/main/java/fun/jinying/nio/ZeroCopyFileTransport.java"),new File("/tmp/ZeroCopyFileTransport.java"));
    }
}