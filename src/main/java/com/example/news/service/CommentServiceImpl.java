package com.example.news.service;


import com.example.news.dao.model.Comments;
import com.example.news.dao.model.News;
import com.example.news.dao.model.Users;
import com.example.news.dao.repository.CommentRepository;
import com.example.news.dao.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final NewsService newsService;
    private final NewsRepository newsRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, NewsService newsService, NewsRepository newsRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;

        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<Comments> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comments findCommentsById(int id) {
        return commentRepository.findCommentsById(id);
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void save(Comments comments, int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUsersByUserName(authentication.getName());
        News news1 = newsRepository.findAll().stream().filter(news -> news.getId()==id).findAny().orElse(null);
        Comments comments1 = new Comments();
        comments1.setUsers(userService.findUsersById(user.getId()));
        comments1.setTextComment(comments.getTextComment());
        comments1.setNews(news1);
        commentRepository.save(comments1);
    }
}
