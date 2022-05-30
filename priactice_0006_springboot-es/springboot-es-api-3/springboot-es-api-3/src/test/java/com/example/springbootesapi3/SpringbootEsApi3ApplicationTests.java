package com.example.springbootesapi3;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * 1.anzuang https://blog.csdn.net/sinat_38852244/article/details/122840506
 *
 */

@SpringBootTest
class SpringbootEsApi3ApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {
    }



    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest createIndexRequest =new CreateIndexRequest("my_zgd");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

}
