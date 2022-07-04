package com.example.news.dao.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    @Email(message = "enter your email, for example: ivanov@mail.ru")
    private String email;

    @Column(unique = true)
    private String userName;



    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "users",
     cascade = CascadeType.ALL)
    private List<News> newsList;

    @OneToMany(mappedBy = "users",
    cascade = CascadeType.ALL)
    private List<Comments> commentsList;








}
