package com.abhineet.social.Service;

import com.abhineet.social.model.databaseAccessObject.UserDao;
import com.abhineet.social.model.databaseObject.User;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public Acknowledgement addUser(User user){
        Acknowledgement acknowledgement= new Acknowledgement();
        try {
            userDao.addUser(user);
        }catch(Exception e){
            acknowledgement.setSuccess(false);
            acknowledgement.setMessage(e.getMessage());
            return acknowledgement;
        }
        acknowledgement.setSuccess(true);
        acknowledgement.setMessage("successfully added the user");
        return acknowledgement;
    }
    public User getUserById(String id){
        return userDao.getUserById(id);
    }
}
