package org.sangdn.example.spring.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sangdn.example.spring.App;
import org.sangdn.example.spring.configuration.TestAppConfig;
import org.sangdn.example.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sangdn on 10/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestAppConfig.class)

//@ContextConfiguration(classes =
//        TestAppConfig.class)

public class UserSearchableRepositoryTest {
    @Autowired
    public void setUserSearchableRepository(UserSearchableRepository userSearchableRepository) {
        this.userSearchableRepository = userSearchableRepository;
    }
    protected UserSearchableRepository userSearchableRepository;

    @Test
    public void testFindByName() throws Exception {
        int n = 100;
        for (int i = 0; i < n; ++i) {
            User user = new User();
            user.setId("u" + i);
            user.setName("name " + n);
            userSearchableRepository.save(user);
        }


        User user1 = userSearchableRepository.findOne("u1");
        assert user1.getId().equals("u1");

        for (User user2 : userSearchableRepository.findAll()) {
            System.out.println(user2.toString());
        }

        PageRequest pageRequest = new PageRequest(1, 10);

        Page<User> name1 = userSearchableRepository.findByName("name 5", pageRequest);
        for (User u : name1) {
            System.out.println(u);
        }




    }
}