package com.abhineet.social.model.databaseAccessObject;

import com.abhineet.social.model.databaseObject.MutualFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MutualFriendDao {
    private static final Logger logger = LoggerFactory.getLogger(MutualFriendDao.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addOrIncrementMutualFriend(Map<String, String> friendMap){
        logger.info("inside add or increment mutual friends for {}",friendMap);
        String query = "INSERT INTO mutual_friends (friend1Id, friend2Id, count) VALUES (?, ?, ?) on duplicate key update count=count+1";
        List<Object[]> objects= new ArrayList<>();

        for(Map.Entry<String, String> friend:  friendMap.entrySet()){
            Object[] object1=  new Object[3];
            object1[2]=1;
            object1[0]=friend.getKey();
            object1[1]=friend.getValue();
            objects.add(object1);
            Object[] object2=  new Object[3];
            object2[2]=1;
            object2[0]=friend.getValue();
            object2[1]=friend.getKey();
            objects.add(object2);
        }
        jdbcTemplate.batchUpdate(query,objects,new int[]{JDBCType.VARCHAR.getVendorTypeNumber(),
                JDBCType.VARCHAR.getVendorTypeNumber(),
                JDBCType.INTEGER.getVendorTypeNumber()});
    }
    public MutualFriend getMutualFriend(String friend1, String friend2){
        String sql =  "select * from mutual_friends where friend1Id=? and friend2Id=?";
        logger.info("going to run the query = {}", sql);
        MutualFriend mutualFriend=null;
        try {
            mutualFriend=(MutualFriend) jdbcTemplate.queryForObject(sql,new Object[]{friend1, friend2}, new MutualFriend());
        }catch (Exception e){
            logger.error("error while getting mutual friends list {}", e.getMessage());
            mutualFriend=new MutualFriend();
            mutualFriend.setFriend1Id(friend1);
            mutualFriend.setFriend2Id(friend2);
            mutualFriend.setCount(0);
        }
        return mutualFriend;
    }
}
