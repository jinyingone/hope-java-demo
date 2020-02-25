package fun.jinying.demo.es;

import org.apache.http.HttpStatus;
import org.elasticsearch.client.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RestEsClientDemoTest {
    static RestEsClientDemo restEsClientDemo;

    @BeforeAll
    public static void setUp() {
        restEsClientDemo = new RestEsClientDemo();
        restEsClientDemo.initClient();
    }

    @Test
    void syncRequest() throws IOException {
        Response response = restEsClientDemo.syncRequest();
        System.out.println(response);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void asyncRequest() throws InterruptedException {
        restEsClientDemo.asyncRequest();
        Thread.sleep(5000);
    }

}