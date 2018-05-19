package com.abhineet.social.model.databaseAccessObject;

import com.abhineet.social.model.databaseObject.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
https://www.dineshonjava.com/implementing-rowmapper-chapter-34/

 */
@Repository
public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addUser(User user){
    String sql = "insert into user (userId,friendUserIds,dateOfBirth,gender,currentCity,sessionIds,postIds,password,email)" +
        "values(?,?,?,?,?,?,?,?,?)";
        logger.info("going to execute query = {}", sql);
        jdbcTemplate.update(sql,user.getUserId(),user.getFriendUserIds(),dateFormat.format(user.getDateOfBirth()),user.getGender(),user.getCurrentCity(),user.getSessionIds(),user.getPostIds(),user.getPassword(),user.getEmail());

    }
    public User getUserById(String userId){
        String sql =  "select * from user where userId=?";
        logger.info("going to run the query = {}", sql);
        User user=(User) jdbcTemplate.queryForObject(sql,new Object[]{userId}, new User());
        return user;
    }
}
