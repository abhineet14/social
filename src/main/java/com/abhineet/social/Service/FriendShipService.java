package com.abhineet.social.Service;

import com.abhineet.social.model.databaseAccessObject.MutualFriendDao;
import com.abhineet.social.model.databaseAccessObject.UserDao;
import com.abhineet.social.model.databaseObject.MutualFriend;
import com.abhineet.social.model.databaseObject.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FriendShipService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MutualFriendDao mutualFriendDao;
    private static final Logger logger = LoggerFactory.getLogger(FriendShipService.class);

    public void addMutualFriends(String friend1, String friend2){
        logger.info("going to process the mutual frinds for {} and {}", friend1,friend2);
        User friend1DO=userDao.getUserById(friend1);
        User friend2DO=userDao.getUserById(friend2);
        if(friend1DO==null || friend2DO==null)
            return;
        List<String> friendsOf1=friend1DO.getFriendUserIds();
        List<String> friendsOf2=friend2DO.getFriendUserIds();
        logger.info("friends of {} = {}", friend1,friendsOf1);
        logger.info("friends of {} = {}", friend2,friendsOf2);
        Map<String,String> friendShipMap1=getFriendShipMap(friendsOf1,friend1,friend2);
        Map<String,String> friendShipMap2=getFriendShipMap(friendsOf2,friend2,friend1);
        logger.info("friendShiMaps to be added = {} and {}", friendShipMap1, friendShipMap2);
        mutualFriendDao.addOrIncrementMutualFriend(friendShipMap1);
        mutualFriendDao.addOrIncrementMutualFriend(friendShipMap2);
    }
    public MutualFriend getMutualFriends(String friend1, String friend2){
        return  mutualFriendDao.getMutualFriend(friend1,friend2);
    }

    private Map<String,String> getFriendShipMap(List<String> friend1FriendList, String friend1, String friend2){
        Map<String,String> friendShipMap = new HashMap<>();
        for(String friend:  friend1FriendList){
            if(!friend.equals(friend2)){
                friendShipMap.put(friend,friend2);
            }
        }
        return  friendShipMap;
    }
}
