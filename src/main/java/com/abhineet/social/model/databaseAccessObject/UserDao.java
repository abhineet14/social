package com.abhineet.social.model.databaseAccessObject;

import com.abhineet.social.helper.ListHelper;
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

    @Autowired
    private ListHelper listHelper;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addUser(User user){
        String sql = "insert into user (userId,friendUserIds,dateOfBirth,gender,currentCity,sessionIds,postIds,password,email)" +
                "values(?,?,?,?,?,?,?,?,?)";
        logger.info("going to execute query = {}", sql);
        jdbcTemplate.update(sql,user.getUserId(), listHelper.convertListToCsv(user.getFriendUserIds()),
                dateFormat.format(user.getDateOfBirth()),user.getGender(),user.getCurrentCity(),
                listHelper.convertListToCsv(user.getSessionIds()),listHelper.convertListToCsv(user.getPostIds()),
                user.getPassword(),user.getEmail());

    }
    public void updateUser(User user){
        String sql = "update user set friendUserIds = ?,dateOfBirth = ?,gender = ?,currentCity = ?,sessionIds = ?" +
                ",postIds = ?,password = ?,email = ? where userId= ?";
        logger.info("going to execute query = {}", sql);
        jdbcTemplate.update(sql, listHelper.convertListToCsv(user.getFriendUserIds()),
                dateFormat.format(user.getDateOfBirth()),user.getGender(),user.getCurrentCity(),
                listHelper.convertListToCsv(user.getSessionIds()),listHelper.convertListToCsv(user.getPostIds()),
                user.getPassword(),user.getEmail(),user.getUserId() );
    }
    public User getUserById(String userId){
        String sql =  "select * from user where userId=?";
        logger.info("going to run the query = {}", sql);
        User user=null;
        try {
            user=(User) jdbcTemplate.queryForObject(sql,new Object[]{userId}, new User());
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
