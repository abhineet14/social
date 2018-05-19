package com.abhineet.social.controller;

import com.abhineet.social.Service.UserService;
import com.abhineet.social.model.databaseObject.User;
import com.abhineet.social.model.response.Acknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private  UserService userService;
    @RequestMapping(path = "add")
    public @ResponseBody Acknowledgement addUser(@RequestBody  User user){
        return userService.addUser(user);
    }

    @RequestMapping(path = "getById")
    public @ResponseBody User getUser(@RequestParam  String userId){
        return userService.getUserById(userId);
    }

    @RequestMapping(path = "addFriend")
    public @ResponseBody Acknowledgement addFriend(@RequestParam String userId, @RequestParam String friendId){
        return  userService.addFriend(userId,friendId);
    }
}
