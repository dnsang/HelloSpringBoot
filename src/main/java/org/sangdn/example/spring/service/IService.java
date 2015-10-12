package org.sangdn.example.spring.service;

import org.sangdn.example.spring.domain.User;

/**
 * Created by sangdn on 10/10/15.
 */
public interface IService {
    public Integer addUser(String name);
    public User getUser(int id);
}
