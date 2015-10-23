package org.sangdn.example.spring.configuration;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.sangdn.example.spring.App;
import org.sangdn.example.spring.controllers.UserController;
import org.sangdn.example.spring.repositories.RepositoryImpl;
import org.sangdn.example.spring.repositories.UserSearchableRepository;
import org.sangdn.example.spring.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

/**
 * Created by sangdn on 10/17/15.
 */
//@SpringBootApplication(exclude = {App.class,ElasticSearchConfig.class, ElasticsearchDataAutoConfiguration.class})
//
//@ComponentScan(basePackages = "org.sangdn.example.spring.*")
//@EnableAutoConfiguration(exclude = {ElasticSearchConfig.class, ElasticsearchDataAutoConfiguration.class})

//@Configuration
//@ComponentScan(basePackages = "org.sangdn.example.spring",
//excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = ElasticSearchConfig.class)})


//    @SpringBootApplication(exclude = {
//            App.class,
//            ElasticSearchConfig.class,
//            ElasticsearchDataAutoConfiguration.class,
//            ElasticsearchAutoConfiguration.class})
    @Configuration
    @ComponentScan(basePackages = {"org.sangdn.example.spring"},
    excludeFilters = @ComponentScan.Filter(type =  FilterType.ASSIGNABLE_TYPE ,
            value = {App.class,ElasticSearchConfig.class}) )
    @Import(value = {ServiceImpl.class,
            RepositoryImpl.class,
            UserController.class,
            ElasticSearchTestConfig.class
    })



public class TestAppConfig {

//    static {
//        System.setProperty("spring.config.location", "classpath:application.properties");
//    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestAppConfig.class, args);
        System.out.println("All Registered Bean");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        for (String bean : beanDefinitionNames){
            System.out.println(bean);
        }

    }

    @Bean
    public Logger logger() {
        return Logger.getLogger(TestAppConfig.class);
    }


}
