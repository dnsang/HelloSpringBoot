package org.sangdn.example.spring.service;

import org.sangdn.example.spring.domain.User;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sangdn on 10/10/15.
 */
public interface IService {
    public boolean addUser(User user);
    public User getUser(String id);

    public List<User> findByName(String name);
    public List<User> findLikeName(String name);
    public int getNumUser();
    public Stream<User> findAllUser();

}
