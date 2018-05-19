package com.abhineet.social.model.databaseObject;

import com.abhineet.social.helper.ListHelper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements RowMapper<User> {
    private String userId;
    private String email;
    private String password;
    private List<String> friendUserIds=new ArrayList<>();
    private Date dateOfBirth;
    private String gender;
    private String currentCity;
    private List<String> sessionIds=new ArrayList<>();
    private List<String> postIds=new ArrayList<>();

    public List<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<String> postIds) {
        this.postIds = postIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFriendUserIds() {
        return friendUserIds;
    }

    public void setFriendUserIds(List<String> friendUserIds) {
        this.friendUserIds = friendUserIds;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<String> getSessionIds() {
        return sessionIds;
    }

    public void setSessionIds(List<String> sessionIds) {
        this.sessionIds = sessionIds;
    }


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        ListHelper listHelper = new ListHelper();
        user.setUserId(resultSet.getString("userId"));
        user.setCurrentCity(resultSet.getString("currentCity"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        user.setEmail(resultSet.getString("email"));
        user.setGender(resultSet.getString("gender"));
        user.setFriendUserIds( listHelper.convertCsvToList(resultSet.getString("friendUserIds")));
        user.setSessionIds(listHelper.convertCsvToList(resultSet.getString("sessionIds")));
        user.setPostIds(listHelper.convertCsvToList(resultSet.getString("postIds")));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
