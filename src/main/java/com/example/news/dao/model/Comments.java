package com.example.news.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments extends BaseEntity{

    @Column(name = "text_comment")
    private String textComment;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne()
    @JoinColumn(name = "news_id")
    private News news;

}
