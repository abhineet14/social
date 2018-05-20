package com.abhineet.social.Service;

import com.abhineet.social.model.databaseAccessObject.PostDao;
import com.abhineet.social.model.databaseObject.Comment;
import com.abhineet.social.model.databaseObject.Post;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService
{
    @Autowired
    private PostDao postDao;

    public Acknowledgement addPost(Post post)
    {
        Acknowledgement acknowledgement = new Acknowledgement();
        try
        {
            this.postDao.addPost(post);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            acknowledgement.setSuccess(false);
            acknowledgement.setMessage(e.getMessage());
            return acknowledgement;
        }
        acknowledgement.setSuccess(true);
        acknowledgement.setMessage("post was successfully added");
        return acknowledgement;
    }

    public Post getPostById(int id)
    {
        return this.postDao.getPostById(id);
    }

    public Acknowledgement addComment(Comment comment)
    {
        Acknowledgement acknowledgement = new Acknowledgement();
        try
        {
            this.postDao.addComment(comment);
        }
        catch (Exception e)
        {
            acknowledgement.setSuccess(false);
            acknowledgement.setMessage(e.getMessage());
            return acknowledgement;
        }
        acknowledgement.setSuccess(true);
        acknowledgement.setMessage("successfully added the comment");
        return acknowledgement;
    }

    public Comment getCommentById(int commentId)
    {
        return this.postDao.getCommentById(commentId);
    }

    public Acknowledgement incrementLikes(int commentId)
    {
        return this.postDao.incrementLikes(commentId);
    }
}
