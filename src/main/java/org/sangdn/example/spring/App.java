package org.sangdn.example.spring;

import org.apache.log4j.Logger;
import org.sangdn.example.spring.domain.User;
import org.sangdn.example.spring.service.IService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * Created by sangdn on 10/10/15.
 */
@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println("All Registered Bean");
    for (String bean : context.getBeanDefinitionNames()){
        System.out.println(bean);
    }
//        ElasticsearchTemplate elasticsearchTemplate = (ElasticsearchTemplate) context.getBean("elasticsearchTemplate");

    }

    @Bean
    public static Logger logger(){
        return Logger.getLogger(App.class);
    }
}
