package org.sangdn.example.spring.controllers;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sangdn.example.spring.App;
import org.sangdn.example.spring.configuration.ElasticSearchTestConfig;
import org.sangdn.example.spring.configuration.TestAppConfig;
import org.sangdn.example.spring.domain.User;
import org.sangdn.example.spring.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;

/**
 * Created by sangdn on 10/12/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
@IntegrationTest
@TestPropertySource(locations = "classpath:application.properties")

public class UserControllerTest {
    @Autowired
    protected IService service;
    @Value("${local.sever.port}")
    int port;

    User u1,u2,u3;
    @Before
    public void setUp(){
        // setup data for test
        u1 = new User(); u1.setId("1"); u1.setName("User1");
        u2 = new User(); u2.setId("2"); u2.setName("User2");
        u3 = new User(); u3.setId("3"); u3.setName("User3");

        service.addUser(u1);
        service.addUser(u2);
        service.addUser(u3);
        //setup RestAssured for test
        RestAssured.port = port;

    }


    @Test
    public void testSayHello() throws Exception {
        when().get("/hello?id=" + u1.getId()).
        then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.is(u1.getId()))
                .body("name", Matchers.is(u1.getName()));
    }

    @Test
    public void testAddNewUser() throws Exception {
//        Map newUser = new HashMap();
//        newUser.put("id",11);
//        newUser.put("name","User11");
//        when().post("/add",newUser)
//        .then()
//                .statusCode(HttpStatus.SC_OK)
//                .body("result", Matchers.is(true));

        given()
                .formParam("id","11")
                .formParam("name","User11")
        .expect()
                .statusCode(HttpStatus.SC_OK)
                .body("result",Matchers.is(true))
        .when().post("/add");

    }

    @Test
    public void testDelete() throws Exception {
        when().post("/delete/1")
        .then().statusCode(HttpStatus.SC_OK)
                .body("result",Matchers.is(false))
                .body("message",Matchers.containsString("Unsupported"));
    }
}