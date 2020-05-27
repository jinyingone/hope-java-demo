package fun.jinying;

import net.bytebuddy.asm.Advice;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * @author jy
 * @date 2020/5/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {
    @Autowired
   private WebServiceTemplate webServiceTemplate;
    @Test
    public void test() {
        webServiceTemplate.sendSourceAndReceive()
    }
}
