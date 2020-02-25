package fun.jinying.demo.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.*;

import java.io.IOException;

/**
 * @description: 原生的restClient方式处理
 * @author: sjy
 * @create: 2019-12-27 15:07
 **/
public class RestEsClientDemo {
    private RestClient restClient;

    /**
     * RestClient 是线程安全的
     * 应该和应用保持同样的生命周期,只有在确定不再使用的时候才需要关闭
     *
     * @return
     */
    public RestClient initClient() {
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("10.16.70.244", 9200, "http"));
        /*设置超时时间*/
        builder.setRequestConfigCallback(configBuilder -> configBuilder.setConnectTimeout(1000).setSocketTimeout(1000));

        builder.setHttpClientConfigCallback(configBuilder ->
                /*设置IO线程数*/
                configBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build())
                        /*设置认证信息*/
                        .setDefaultCredentialsProvider(basicCredentialsProvider));
        ;
        restClient = builder.build();
        return restClient;
    }

    /**
     * 同步请求
     *
     * @return
     * @throws IOException
     */
    public Response syncRequest() throws IOException {
        Request request = new Request("get", "/");
        return restClient.performRequest(request);
    }

    /**
     * 异步请求
     */
    public void asyncRequest() {
        Request request = new Request("get", "/");
        restClient.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("failure");
            }
        });
    }

    public void close() throws IOException {
        if (restClient != null) {
            restClient.close();
        }
    }
}
