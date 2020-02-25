package fun.jinying.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 高级 api
 * @author: sjy
 * @create: 2019-12-30 17:39
 **/
public class RestHighLevelClientDemo {
    private RestHighLevelClient restHighLevelClient;

    /**
     * RestHighLevelClient 里面创建了一个低级别连接池,
     * close方法可以关闭内部的低级别连接池
     *
     * @return
     */
    public RestHighLevelClient initClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("10.16.70.244", 9200, "http"));
        restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }

    public void close() throws IOException {
        if (restHighLevelClient != null) {
            restHighLevelClient.close();
        }
    }

    /**
     * 创建索引
     *
     * @return
     * @throws IOException
     */
    public boolean createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("demo-index");
        //IK分词
        Map<String, Object> contentMap = new HashMap<>(8);
        contentMap.put("type", "text");
        contentMap.put("analyzer", "ik_max_word");
        contentMap.put("search_analyzer", "ik_smart");



        Map<String, Object> propertiesMap = new HashMap<>(8);
        propertiesMap.put("properties", Collections.singletonMap("content", contentMap));
        createIndexRequest.mapping(propertiesMap);
        IndicesClient indices = restHighLevelClient.indices();
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @return
     * @throws IOException
     */
    public boolean deleteIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("demo-index");
        if (restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT)) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("demo-index");
            boolean acknowledged = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT).isAcknowledged();
            return acknowledged;
        }
        return false;
    }

    /**
     * 保存数据,通过json格式
     *
     * @param json
     * @return
     * @throws IOException
     */
    public boolean createDocumentByJson(String id, String json) throws IOException {
        IndexRequest indexRequest = new IndexRequest("demo-index");
        indexRequest.id(id);
        indexRequest.source(json, XContentType.JSON);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT).status() == RestStatus.OK;
    }

    /**
     * 保存文件,通过map形式
     *
     * @param map
     * @return
     * @throws IOException
     */
    public boolean createDocumentByMap(Map<String, Object> map) throws IOException {
        IndexRequest indexRequest = new IndexRequest("demo-index");
        indexRequest.source(map);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT).status() == RestStatus.OK;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     * @throws IOException
     */
    public Map<String, Object> getDocumentById(String id) throws IOException {
        GetRequest getRequest = new GetRequest("demo-index", "1");
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT).getSourceAsMap();
    }

    public SearchResponse searchDocument() throws IOException {
        SearchRequest searchRequest = new SearchRequest("demo-index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", "zhongguo"));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
