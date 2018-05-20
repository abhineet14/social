package com.abhineet.social.model.databaseAccessObject;

import com.abhineet.social.model.databaseObject.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class PostDao {

    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addPost(Post post){
        String sql = "insert into post (value,created_on,commentId,userId)" +
                "values(?,?,?,?)";
        logger.info("going to execute query = {}", sql);
        jdbcTemplate.update(sql,post.getValue(),new Date(),post.getCommentIds(),post.getUserId());

    }
}
