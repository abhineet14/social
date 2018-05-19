package com.abhineet.social.Service;

import com.abhineet.social.helper.Producer;
import com.abhineet.social.model.databaseAccessObject.UserDao;
import com.abhineet.social.model.databaseObject.User;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    Producer producer;
    @Autowired
    Queue<String> friendShipQueue;
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

    public Acknowledgement addFriend(String userId, String friendId){
        User user = userDao.getUserById(userId);
        User friend = userDao.getUserById(friendId);
        Acknowledgement acknowledgement =  new Acknowledgement();
        if(user==null || friend==null){
            acknowledgement.setMessage("invalid user Id");
            acknowledgement.setSuccess(false);
            return acknowledgement;
        }
        if(user.getFriendUserIds().contains(friendId) ||  friend.getFriendUserIds().contains(userId)){
            acknowledgement.setSuccess(false);
            acknowledgement.setMessage("they are already friend");
            return acknowledgement;
        }
        user.getFriendUserIds().add(friendId);
        friend.getFriendUserIds().add(userId);
        userDao.updateUser(user);
        userDao.updateUser(friend);
        acknowledgement.setMessage("added freind");
        acknowledgement.setSuccess(true);
        producer.produce(userId+":"+friendId,friendShipQueue );
        return  acknowledgement;

    }
}
