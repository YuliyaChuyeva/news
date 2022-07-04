package com.example.news.web.controller;

import com.example.news.dao.model.Comments;
import com.example.news.dao.model.News;
import com.example.news.dao.model.Role;
import com.example.news.dao.model.Users;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final NewsService newsService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, NewsService newsService, UserService userService) {
        this.commentService = commentService;
        this.newsService = newsService;
        this.userService = userService;
    }

    @ModelAttribute("newComment")
    public Comments getEmptyComment(){
        return new Comments();
    }

    @GetMapping("/all/comment")
    public String getAllComments(Model model){
        List<Comments> comments=commentService.findAll();
        model.addAttribute("comments", comments);
        return "firstPage";
    }

    @GetMapping("/comment/{id}")
    public String getCommentsById(Model model,@PathVariable("id") int id){
        Comments commentsList = commentService.findCommentsById(id);
        model.addAttribute("commentList",commentsList);
        return "firstPage";
    }

    @GetMapping("/commentNews/{id}")
    public String getCommentPage(Model model, @PathVariable("id") int id){
        News newsListByCom= newsService.findNewsById(id);
        List<Comments> comments = new ArrayList<>();
        for (Comments comments1 : commentService.findAll()) {
            if (comments1.getNews().getId() == newsListByCom.getId()) {
                comments.add(comments1);
            }
        }
        model.addAttribute("newsListByCom",newsListByCom);
        model.addAttribute("comments", comments);

        return "commentPage";
    }

  @PostMapping("/createComment/{id}")
    public String createComment(Comments comments,Model model, @PathVariable("id") int id){
      News newsListByCom= newsService.findNewsById(id);
      model.addAttribute("newsListByCom",newsListByCom);
        commentService.save(comments,id );
        return "homePage";
  }

 @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") int id, Principal principal){
     Users users = userService.findUsersByUserName(principal.getName());
     Role role = users.getRole();
     if(role==Role.ROLE_ADMIN){
        commentService.deleteById(id);}
        return "homePage";
  }
}
