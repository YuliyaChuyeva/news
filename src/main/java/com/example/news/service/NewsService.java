package com.example.news.service;

import com.example.news.dao.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {


    List<News> findAll();
    News findNewsById(int id);
    News findNewsByTitle(String title);
    void save(News news);
    void update(int id, News news);

    void deleteById(int id);

    List<News> findAll(String keyword);

}
