package org.sangdn.example.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sangdn.example.spring.configuration.TestAppConfig;
import org.sangdn.example.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sangdn on 10/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)

//@SpringApplicationConfiguration(classes = App.class)
@SpringApplicationConfiguration(classes = TestAppConfig.class)
public class ServiceImplTest {

    @Autowired
    ServiceImpl serviceImpl;

    @org.junit.Test
    public void testProcess() throws Exception {
        User u = new User();
        u.setId("1");
        u.setName("UserName");
        serviceImpl.addUser(u);
        User user = serviceImpl.getUser(u.getId());
        assert user.getName().equals(u.getName());
    }

    @Test
    public void testSearch() {
        User u = new User();
        u.setId("1");
        u.setName("username");
        serviceImpl.addUser(u);


        List<User> userName = serviceImpl.findByName("username");

        System.out.println(Arrays.toString(userName.toArray()));

        List<User> username = serviceImpl.findLikeName("username");
        System.out.println(Arrays.toString(userName.toArray()));

        System.out.println("Total User In ES: " + serviceImpl.getNumUser());
    }
}