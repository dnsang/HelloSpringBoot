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
    @Autowired
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    protected IRepository repository;

    public void process(User obj) {
        repository.put(obj);
        System.out.println("Process : " + repository.get().getName());

    }
}
