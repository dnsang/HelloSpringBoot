package org.sangdn.example.spring.service;

import org.apache.commons.collections.ArrayStack;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.sangdn.example.spring.domain.User;
import org.sangdn.example.spring.repositories.IRepository;
import org.sangdn.example.spring.repositories.UserSearchableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by sangdn on 10/10/15.
 */
@Service
public class ServiceImpl implements IService {
    @Autowired
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Autowired
    public void setUserSearchableRepository(UserSearchableRepository userSearchableRepository) {
        this.userSearchableRepository = userSearchableRepository;
    }

    protected  ElasticsearchTemplate elasticsearchTemplate;
    protected Client client;
    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    protected IRepository repository;
    protected UserSearchableRepository userSearchableRepository;

    @Override
    public boolean addUser(User user) {
        repository.put(user);
        userSearchableRepository.save(user);
        return true;
    }

    @Override
    public User getUser(String id) {
        return repository.get(id);
    }

    @Override
    public List<User> findByName(String name) {
        PageRequest pageRequest = new PageRequest(1, 50);
        return userSearchableRepository.findByName(name, pageRequest).getContent();
    }

    /**
     * Using native client to excute search request
     * @param name find all user with name like @name
     * @return List User
     */
    @Override
    public List<User> findLikeName(String name) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("springboot");
        searchRequestBuilder.setTypes("user");
        searchRequestBuilder.setQuery(QueryBuilders.wildcardQuery("name", name));
        searchRequestBuilder.addField("name");
        searchRequestBuilder.setFrom(0);
        searchRequestBuilder.setSize(10);
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        List<User> result = new ArrayList<User>();
        for (SearchHit hit : searchResponse.getHits()){
            User u = new User();
            u.setId(hit.getId());
            u.setName(hit.getFields().get("name").getValue().toString());
            result.add(u);
        }
        return result;
    }

    /**
     * Using elasticsearchTemplate to excute native query
     * @return total user
     */
    @Override
    public int getNumUser() {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withIndices("springboot").withTypes("user")
                .withSearchType(SearchType.COUNT).withQuery(QueryBuilders.matchAllQuery()).build();
        return (int)elasticsearchTemplate.count(query);
    }

    @Override
    public Stream<User> findAllUser() {
        Iterable<User> allUser = userSearchableRepository.findAll();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize((Iterator<User>) allUser, Spliterator.ORDERED), false);
    }
}
