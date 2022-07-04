package com.example.news.dao.repository;


import com.example.news.dao.model.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comments,Integer> {
    List<Comments> findAll();
    Comments findCommentsById(int id);
}
