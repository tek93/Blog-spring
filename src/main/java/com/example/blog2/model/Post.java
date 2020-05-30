package com.example.blog2.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@CrossOrigin(origins = "*")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String author;

    public Post(String name, String description, String author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
