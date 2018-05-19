package com.abhineet.social.model.databaseObject;

import com.abhineet.social.helper.ListHelper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Post implements RowMapper<Post>
{
    private String postId;
    private String value;
    private Date created_on;
    private String userId;
    private List<String> commentIds;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();
        ListHelper listHelper=new ListHelper();
        post.setPostId(resultSet.getString("postId"));
        post.setValue(resultSet.getString("value"));
        post.setCreated_on(resultSet.getDate("created_on"));
        post.setUserId(resultSet.getString("userId"));
        post.setCommentIds(listHelper.convertCsvToList(resultSet.getString("commentId")));
        return post;
    }
}
