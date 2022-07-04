package com.example.news.service;


import com.example.news.dao.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> findAll();
    Users findUsersById(int id);
    Users findUsersByUserName(String userName);
    void save(Users user);


}
