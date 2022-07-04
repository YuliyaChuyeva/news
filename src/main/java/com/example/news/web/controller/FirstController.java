package com.example.news.web.controller;


import com.example.news.dao.model.News;
import com.example.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class FirstController {
    private final NewsService newsService;

    @Autowired
    public FirstController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/")
    public String getFirstPage(){
        return "firstPage";
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        List<News> newsListHome = newsService.findAll();
        Collections.reverse(newsListHome);
        model.addAttribute("newsListHome",newsListHome);

        return "homePage";
    }

    @GetMapping("/home/search")
    public String findSearchNews(Model model, @Param("keyword") String keyword,
                                 @RequestParam(value = "page",required = false,defaultValue = "0")Integer page){
        List<News> newsListHome = newsService.findAll(keyword);
        Collections.reverse(newsListHome);
        model.addAttribute("newsListHome",newsListHome);
        model.addAttribute("keyword", keyword);



        return "homePage";
    }

}
