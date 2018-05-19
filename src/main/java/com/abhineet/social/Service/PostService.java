package com.abhineet.social.Service;

import com.abhineet.social.model.databaseAccessObject.PostDao;
import com.abhineet.social.model.databaseObject.Post;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired private PostDao postDao;
    public Acknowledgement addPost(Post post)
    {
        Acknowledgement acknowledgement= new Acknowledgement();
        try {
            acknowledgement.setMessage("In try block");
            postDao.addPost(post);
        }catch(Exception e){
            acknowledgement.setSuccess(false);
            acknowledgement.setMessage(e.getMessage());
            return acknowledgement;
        }
        acknowledgement.setSuccess(true);
        acknowledgement.setMessage("successfully added the post");
        return acknowledgement;
    }


}
