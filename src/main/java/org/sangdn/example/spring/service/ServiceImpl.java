package org.sangdn.example.spring.service;

import org.sangdn.example.spring.domain.User;
import org.sangdn.example.spring.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sangdn on 10/10/15.
 */
@Service
public class ServiceImpl implements IService {
    private int id = 0;
    @Autowired
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    protected IRepository repository;

    @Override
    public boolean addUser(User user) {
        repository.put(user);
        return true;
    }

    @Override
    public User getUser(int id) {
        return repository.get(id);
    }
}
