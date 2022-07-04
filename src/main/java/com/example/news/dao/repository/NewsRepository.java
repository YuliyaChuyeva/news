package com.example.news.dao.repository;

import com.example.news.dao.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {

    List<News> findAll();
    News findNewsById(int id);
    News findNewsByTitle(String title);

   @Query("SELECT n FROM News n WHERE "
           + "CONCAT(n.id, n.title, n.text, n.date, n.users) " +
    " LIKE %?1%")
   List<News> findAll(String keyword);


}
