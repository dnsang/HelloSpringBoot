package org.sangdn.example.spring.configuration;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by sangdn on 10/23/15.
 */
@Configuration
@EnableElasticsearchRepositories("org.sangdn.example.spring.repositories")
public class ElasticSearchTestConfig {
    @Autowired
    public Logger logger;

    @Bean
    public Client client() {

        return NodeBuilder.nodeBuilder().clusterName("local-cluster").local(true).node().client();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(Client client) {
        System.out.println("Init ES Local");
        System.out.flush();
        return new ElasticsearchTemplate(client);
    }
    @Bean
    public ElasticsearchOperations elasticsearchTemplate(){
        System.out.println("Init ES Local2");
        System.out.flush();

        return new ElasticsearchTemplate(NodeBuilder.nodeBuilder().clusterName("local-cluster").local(true).node().client());
    }
}
