package org.sangdn.example.spring.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sangdn on 10/17/15.
 */
@SpringBootApplication(exclude = {ElasticSearchConfig.class, ElasticsearchDataAutoConfiguration.class})
@Import(ElasticSearchTestConfig.class)
@ComponentScan(basePackages = "org.sangdn.example.spring.*")

public class TestAppConfig {

//    static {
//        System.setProperty("spring.config.location", "classpath:application.properties");
//    }

    @Bean
    public static Logger logger() {
        return Logger.getLogger(TestAppConfig.class);
    }
}
