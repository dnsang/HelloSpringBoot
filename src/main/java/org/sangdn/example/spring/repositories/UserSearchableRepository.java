package org.sangdn.example.spring.repositories;

import org.sangdn.example.spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Created by sangdn on 10/17/15.
 */
public interface UserSearchableRepository extends ElasticsearchRepository<User,String>{
    Page<User> findByName(String name, Pageable pageable);
}
