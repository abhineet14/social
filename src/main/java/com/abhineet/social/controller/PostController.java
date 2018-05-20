package com.abhineet.social.controller;

import com.abhineet.social.Service.PostService;
import com.abhineet.social.model.databaseObject.Comment;
import com.abhineet.social.model.databaseObject.Post;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path={"post"})
public class PostController
{
    @Autowired
    private PostService postService;

    @RequestMapping(path={"add"})
    @ResponseBody
    public Acknowledgement addPost(@RequestBody Post post)
    {
        return this.postService.addPost(post);
    }

    @RequestMapping(path={"getById"})
    @ResponseBody
    public Post getPost(@RequestParam int postId)
    {
        return this.postService.getPostById(postId);
    }

    @RequestMapping(path={"addComment"})
    @ResponseBody
    public Acknowledgement addComment(@RequestBody Comment comment)
    {
        return this.postService.addComment(comment);
    }

    @RequestMapping(path={"getComment"})
    @ResponseBody
    public Comment getComment(@RequestParam int commentId)
    {
        return this.postService.getCommentById(commentId);
    }

    @RequestMapping(path={"incrementLikes"})
    @ResponseBody
    public Acknowledgement incrementLikes(@RequestBody int commentId)
    {
        return this.postService.incrementLikes(commentId);
    }
}
