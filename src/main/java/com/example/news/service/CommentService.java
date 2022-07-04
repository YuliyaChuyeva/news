package com.example.news.service;


import com.example.news.dao.model.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> findAll();
    void save(Comments comments, int id);
    Comments findCommentsById(int id);

    void deleteById(int id);
}
