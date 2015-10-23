package org.sangdn.example.spring.configuration;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import sun.misc.Unsafe;

import javax.annotation.Resource;
import java.nio.ByteBuffer;

/**
 * Created by sangdn on 10/17/15.
 */
@Configuration
@EnableElasticsearchRepositories("org.sangdn.example.spring.repositories")

public class ElasticSearchConfig {
    @Resource
    private Environment env;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Autowired
    private Logger logger;

    @Bean
    public Client elasticsearchClient() {

        
        System.out.println("Init ES Client");
        System.out.flush();
        if (!env.containsProperty("es.cluster") || !env.containsProperty("es.nodes")) {
            System.err.println("Not found config for es");
            System.err.flush();
            return null;
        }
        String clusterName = env.getProperty("es.cluster");

        String[] nodes = env.getProperty("es.nodes").split(";");
        Settings settings = ImmutableSettings.builder().put("cluster.name", clusterName).build();

        TransportClient client = new TransportClient(settings);
        for (String node : nodes) {
            String[] serverAndPort = node.split(":");
            assert serverAndPort.length == 2;
            client.addTransportAddress(new InetSocketTransportAddress(serverAndPort[0], Integer.parseInt(serverAndPort[1])));
//            logger.info("ElasticSearch Added " + node);
            System.out.println("Added Elasticsearch Node: " + node);
        }
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(Client client) {

        System.out.println("Init ES Template");
        System.out.flush();
        return new ElasticsearchTemplate(client);
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(){
        System.out.println("Init ES Template 2");
        System.out.flush();
        Client client = this.elasticsearchClient();
        return new ElasticsearchTemplate(client);
    }
}
