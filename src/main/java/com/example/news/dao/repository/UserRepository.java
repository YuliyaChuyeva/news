package com.example.news.dao.repository;

import com.example.news.dao.model.News;
import com.example.news.dao.model.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<Users,Integer> {
    List<Users> findAll();
    Users findUsersById(int id);
    Users findUsersByUserName(String userName);

}
