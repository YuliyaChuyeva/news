package com.example.news.service;

import com.example.news.dao.model.News;
import com.example.news.dao.model.Users;
import com.example.news.dao.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    private final NewsRepository newsRepository;
    private final UserServiceImpl userService;


    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, UserServiceImpl userService) {
        this.newsRepository = newsRepository;

        this.userService = userService;

    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findNewsByTitle(String title) {
        return newsRepository.findNewsByTitle(title);
    }

    @Override
    public News findNewsById(int id) {
        return newsRepository.findNewsById(id);
    }

    @Override
    public void save(News news) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUsersByUserName(authentication.getName());
        News news1 = new News();
        news1.setUsers(userService.findUsersById(user.getId()));
        news1.setTitle(news.getTitle());
        news1.setText(news.getText());
        news1.setDate(news.getDate());
        newsRepository.save(news1);
    }

    @Override
    public void update(int id, News newsUp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUsersByUserName(authentication.getName());
        News news2 = newsRepository.findAll().stream().filter(news -> news.getId()==id).findAny().orElse(null);
        news2.setUsers(userService.findUsersById(user.getId()));
        news2.setText(newsUp.getText());
        news2.setTitle(newsUp.getTitle());
        news2.setDate(newsUp.getDate());
        newsRepository.save(news2);
    }

    @Override
    public void deleteById(int id) {
        newsRepository.deleteById(id);
    }





    @Override
    public List<News> findAll(String keyword) {
        return newsRepository.findAll(keyword);
    }

}
