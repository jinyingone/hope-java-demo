package fun.jinying;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * @author jy
 * @date 2020/5/27
 */
@Configuration
public class WsClientConfig {

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://127.0.0.1:8080/ws");
        return webServiceTemplate;
    }
}
