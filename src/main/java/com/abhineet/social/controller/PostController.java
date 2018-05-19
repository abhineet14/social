package com.abhineet.social.controller;

import com.abhineet.social.Service.PostService;
import com.abhineet.social.model.databaseObject.Post;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(path = "add")
    public @ResponseBody  Acknowledgement addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

}
