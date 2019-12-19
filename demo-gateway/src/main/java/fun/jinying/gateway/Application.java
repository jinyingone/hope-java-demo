package fun.jinying.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @description: 入口
 * @author: sjy
 * @create: 2019-11-27 12:07
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public RouterFunction<ServerResponse> jdHandlerRouting(JdHandler jdHandler) {
        return RouterFunctions.route(GET("/**"), jdHandler::handle);
    }

    @Bean
    public JdHandler jdHandler() {
        return new JdHandler();
    }

    private static class JdHandler {
        public Mono<ServerResponse> handle(ServerRequest serverRequest) {
            return ServerResponse.ok().syncBody("Success jd");
        }
    }
}
