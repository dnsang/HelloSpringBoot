package org.sangdn.example.spring.repositories;

import org.sangdn.example.spring.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by sangdn on 10/10/15.
 */
@Repository
public class RepositoryImpl implements IRepository {
    private User obj;
    @Override
    public void put(User obj) {
        this.obj = obj;
    }

    @Override
    public User get() {
        return obj;
    }
}
