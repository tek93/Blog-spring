package com.example.blog2.controller;


import com.example.blog2.model.Post;
import com.example.blog2.model.User;
import com.example.blog2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PostController {



    @Autowired
    private PostRepository postRepository;

    private List<Post> posts ;



    @GetMapping("/posts")
    public List<Post> firstPage() {

                List<Post> posty = postRepository.findAll();
        return posty;
    }


    @DeleteMapping("/post/{id}")
    public Post delete(@PathVariable("id") int id) {
        posts= postRepository.findAll();
        Post deletedPost = null;
        for (Post pos : posts) {
            if (pos.getPostId().equals(id)) {
                posts.remove(pos);
                postRepository.deleteById(pos.getPostId());
                System.out.println(pos);
                deletedPost = pos;
                break;
            }
        }
        return deletedPost;
    }

    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.GET)
    @GetMapping({"post/validateLogin"})
    public User validateLogin(){

        return new User("User logged successful");
    }

    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.POST)
    @PostMapping("/post")
    public Post create(@RequestBody Post news, Authentication authentication) {


       String  name= (authentication.getName());
       news.setAuthor(name);

        

       String desc= news.getDescription();
       String title = news.getName();

       Post post = new Post(title, desc, name);



        postRepository.save(news);
        return news;
    }

}


