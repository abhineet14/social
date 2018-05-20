package com.abhineet.social.model.databaseObject;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MutualFriend implements RowMapper<MutualFriend> {
    private String friend1Id;
    private String friend2Id;
    private int count;
    public String getFriend1Id() {
        return friend1Id;
    }

    public void setFriend1Id(String friend1Id) {
        this.friend1Id = friend1Id;
    }

    public String getFriend2Id() {
        return friend2Id;
    }

    public void setFriend2Id(String friend2Id) {
        this.friend2Id = friend2Id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public MutualFriend mapRow(ResultSet resultSet, int i) throws SQLException {
        MutualFriend mutualFriend = new MutualFriend();
        mutualFriend.setCount(resultSet.getInt("count"));
        mutualFriend.setFriend1Id(resultSet.getString("friend1Id"));
        mutualFriend.setFriend2Id(resultSet.getString("friend2Id"));
        return  mutualFriend;
    }
}
