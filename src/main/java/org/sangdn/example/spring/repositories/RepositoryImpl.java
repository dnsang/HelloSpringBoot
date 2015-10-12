package org.sangdn.example.spring.repositories;

import org.sangdn.example.spring.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by sangdn on 10/10/15.
 */
@Repository
public class RepositoryImpl implements IRepository {
    private Map<Integer,User> users;
    @Override
    public void put(User obj) {
        users.put(obj.getId(),obj);

    }

    @Override
    public User get(Integer id) {
        return users.get(id);
    }
}
