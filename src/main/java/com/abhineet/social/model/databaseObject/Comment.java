package com.abhineet.social.model.databaseObject;

import com.abhineet.social.helper.ListHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

public class Comment
        implements RowMapper<Comment>
{
    private String value;
    private Date created_on;
    private int no_of_likes;
    private String userId;
    private int commentId;

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Date getCreated_on()
    {
        return this.created_on;
    }

    public void setCreated_on(Date created_on)
    {
        this.created_on = created_on;
    }

    public int getNo_of_likes()
    {
        return this.no_of_likes;
    }

    public void setNo_of_likes(int no_of_likes)
    {
        this.no_of_likes = no_of_likes;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getCommentId()
    {
        return this.commentId;
    }

    public void setCommentId(int commentId)
    {
        this.commentId = commentId;
    }

    public Comment mapRow(ResultSet resultSet, int i)
            throws SQLException
    {
        Comment comment = new Comment();
        ListHelper listHelper = new ListHelper();
        comment.setValue(resultSet.getString("value"));
        comment.setCreated_on(resultSet.getDate("date"));
        comment.setNo_of_likes(resultSet.getInt("number_of_likes"));
        comment.setUserId(resultSet.getString("userId"));
        comment.setCommentId(resultSet.getInt("CommentId"));
        return comment;
    }
}
