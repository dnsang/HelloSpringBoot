package org.sangdn.example.spring.repositories;

import org.sangdn.example.spring.domain.User;

import java.util.Objects;

/**
 * Created by sangdn on 10/10/15.
 */
public interface IRepository {
    public  void put(User obj);
    public  User get(String id);
}
