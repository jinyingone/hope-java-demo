import com.hope.demo.kafka.DemoApplication;
import com.hope.demo.kafka.KafkaSenderDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 发送测试
 *
 * @author jy
 * @date 2019-05-24 下午2:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class SenderTest {
    @Autowired
    private KafkaSenderDemo kafkaSenderDemo;

    @Test
    public void testSend() throws IOException {
        kafkaSenderDemo.sendMsg("sns_hycs_anal_log_test", "kafkaSenderDemo");
        System.in.read();
    }
}
