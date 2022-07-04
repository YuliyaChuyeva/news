package com.example.news.service;

import com.example.news.dao.model.Role;
import com.example.news.dao.model.Users;
import com.example.news.dao.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
}

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findUsersById(int id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public Users findUsersByUserName(String userName) {
        return userRepository.findUsersByUserName(userName);
    }

    @Override
    public void save(Users user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = findUsersByUserName(username);
        Set<Role> roles = new HashSet<>();
        if(user == null){
            throw new UsernameNotFoundException("Пользователь с именем " + username + " не существует");
        }
        else
            System.out.println("Success");
        roles.add(user.getRole());

    return new User(username,user.getPassword(),roles);
    }
}
