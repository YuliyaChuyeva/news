package com.example.news.web.controller;


import com.example.news.dao.model.News;
import com.example.news.dao.model.Role;
import com.example.news.dao.model.Users;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;

    @Autowired
    public NewsController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    @ModelAttribute("newNews")
    public News getEmptyNews(){
        return new News();
    }

    @GetMapping("/news/{id}")
    public String getNewsById(Model model, @PathVariable("id")int id){
        News news = newsService.findNewsById(id);
        model.addAttribute("someNews",news);
        return "firstPage";
    }



    @GetMapping("/all/news")
    public String getAllNews(Model model, Principal principal){
        List<News> newsList = newsService.findAll();
        Users userByUserName = userService.findUsersByUserName(principal.getName());
        Role role = userByUserName.getRole();
        if(role == Role.ROLE_ADMIN){
            newsList = newsService.findAll();
        }
        else if(role == Role.ROLE_USER){
            newsList = newsService.findAll().stream().filter(news -> news.getUsers().getId() == userByUserName.getId()).collect(Collectors.toList());
        }

        model.addAttribute("news", newsList);
        return "firstPage";
    }


    @GetMapping("/createNews")
    public String getCreateNewPage(){
        return "createNews";
    }

    @PostMapping("/create")
    public String createNewNews( News news){
        newsService.save(news);
        return "homePage";
    }

    @GetMapping("/news/edit/{id}")
    public String editNews(Model model, @PathVariable("id") int id){
        model.addAttribute("newNews",newsService.findNewsById(id));
        return "editNews";
    }

    @PatchMapping("/news/{id}")
    public String updateNews(@ModelAttribute("newNews")News news, @PathVariable("id") int id, String keyword){
        newsService.update(id,news);
        return "homePage";
    }

    @GetMapping ("news/delete/{id}")
    public String deleteNews(@PathVariable("id") int id){
        newsService.deleteById(id);
        return "homePage";
    }

    @GetMapping("news/deleteAdmin/{id}")
    public String deleteAdminNews(@PathVariable("id")int id, Principal principal){
        Users users = userService.findUsersByUserName(principal.getName());
        Role role= users.getRole();
        if(role==Role.ROLE_ADMIN){
            newsService.deleteById(id);
        }
        return "homePage";
    }



}
