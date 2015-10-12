package org.sangdn.example.spring.service;

import org.sangdn.example.spring.domain.User;

/**
 * Created by sangdn on 10/10/15.
 */
public interface IService {
    public boolean addUser(User user);
    public User getUser(int id);
}
