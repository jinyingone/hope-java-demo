package fun.jinying.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 0拷贝的文件传输
 *
 * @author jy
 * @date 2020/4/28
 */
public class ZeroCopyFileTransport {
    public void copy(File src, File dst) throws IOException {
        try (FileChannel srcChannel = new FileInputStream(src).getChannel()) {
            try (FileChannel dstChannel = new FileOutputStream(dst).getChannel()) {
                srcChannel.transferTo(0,src.length(),dstChannel);
            }
        }
    }
}
