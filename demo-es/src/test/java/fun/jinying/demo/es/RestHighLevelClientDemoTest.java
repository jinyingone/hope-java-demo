package fun.jinying.demo.es;

import org.elasticsearch.action.search.SearchResponse;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

class RestHighLevelClientDemoTest {
    static RestHighLevelClientDemo restHighLevelClientDemo;

    @BeforeAll
    public static void setup() {
        restHighLevelClientDemo = new RestHighLevelClientDemo();
        restHighLevelClientDemo.initClient();
    }

    @Test
    public void createIndex() throws IOException {
        boolean index = restHighLevelClientDemo.createIndex();
        System.out.println(index);
        Assertions.assertTrue(index);
    }

    @Test
    void postsDocument() throws IOException {
        restHighLevelClientDemo.createDocumentByJson("1", "{\"content\":\"美国留给伊拉克的是个烂摊子吗\"}");
        restHighLevelClientDemo.createDocumentByJson("2", "{\"content\":\"公安部：各地校车将享最高路权\"}");
        restHighLevelClientDemo.createDocumentByMap(Collections.singletonMap("content", "中韩渔警冲突调查：韩警平均每天扣1艘中国渔船"));
        restHighLevelClientDemo.createDocumentByMap(Collections.singletonMap("content", "中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"));
    }

    @Test
    void getDocumentById() throws IOException {
        Map<String, Object> documentById = restHighLevelClientDemo.getDocumentById("2");
        System.out.println(documentById);
        Assertions.assertNotNull(documentById);
        Assertions.assertTrue(documentById.containsKey("content"));
    }


    @Test
    void deleteIndex() throws IOException {
        boolean b = restHighLevelClientDemo.deleteIndex();
        System.out.println(b);
    }


    @Test
    void searchDocument() throws IOException {
        SearchResponse searchResponse = restHighLevelClientDemo.searchDocument();
        System.out.println(searchResponse);
    }
}