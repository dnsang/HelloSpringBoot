package org.sangdn.example.spring.service;

import org.junit.runner.RunWith;
import org.sangdn.example.spring.App;
import org.sangdn.example.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sangdn on 10/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ServiceImplTest {
    @Autowired
    ServiceImpl serviceImpl;

    @org.junit.Test
    public void testProcess() throws Exception {
        User user = new User();
        user.setName("user-name");
        serviceImpl.process(user);
    }
}