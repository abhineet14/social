package com.abhineet.social.model.databaseAccessObject;

import com.abhineet.social.helper.ListHelper;
import com.abhineet.social.model.databaseObject.Comment;
import com.abhineet.social.model.databaseObject.Post;
import com.abhineet.social.model.response.Acknowledgement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao
{
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    JdbcTemplate jdbcTemplate;

    public PostDao() {}

    private ListHelper listHelper = new ListHelper();
    public int addPost(final Post post)
    {
        final String sql = "insert into post (value,created_on,commentId,userId)values(?,?,?,?)";

        logger.info("going to execute query = {}", sql);

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException
            {
                PreparedStatement statement = con.prepareStatement(sql, 1);
                statement.setString(1, post.getValue());
                statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                statement.setString(3, PostDao.this.listHelper.convertListToCsv(post.getCommentIds()));
                statement.setString(4, post.getUserId());
                return statement;
            }
        }, holder);

        int primaryKey = holder.getKey().intValue();

        logger.info("added the post with id = {}", Integer.valueOf(primaryKey));
        return primaryKey;
    }

  public Post getPostById(int postId)
    {
        String sql = "select * from post where postId=?";
        logger.info("going to run the query = {}", sql);
        Post post = null;
        try {
            post = (Post)jdbcTemplate.queryForObject(sql, new Object[] { Integer.valueOf(postId) }, new Post());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public void addComment(Comment comment) { String sql = "insert into comments (value,date,userId)values(?,?,?)";

        logger.info("going to execute query = {}", sql);
        jdbcTemplate.update(sql, new Object[] { comment.getValue(), new Date(), comment.getUserId() });
    }

    public Comment getCommentById(int commentId) {
        String sql = "select * from comments where commentId=?";
        logger.info("going to run the query = {}", sql);
        Comment comment = null;
        try {
            comment = (Comment)jdbcTemplate.queryForObject(sql, new Object[] { Integer.valueOf(commentId) }, new Comment());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public Acknowledgement incrementLikes(int commentId) {
        String sql = "update comments set number_of_likes=number_of_likes+1 where commentId=" + commentId;

        logger.info("going to run the query = {}", sql);
        Acknowledgement acknowledgement = new Acknowledgement();
        try {
            jdbcTemplate.execute(sql);
            acknowledgement.setMessage("Number of likes incremented by 1");
            acknowledgement.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            acknowledgement.setMessage("Error");
            acknowledgement.setSuccess(false);
        }
        return acknowledgement;
    }
}
