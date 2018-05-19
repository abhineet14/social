package com.abhineet.social.configs;

import com.abhineet.social.helper.Consumer;
import com.abhineet.social.helper.FriendShipConsumer;
import org.springframework.stereotype.Service;

import java.util.Queue;
@Service
public class ConsumerFactory {
    public Consumer getConsumer(String type, Queue<String> queue){
        if("friendShipQueue".equals(type)){
            return new FriendShipConsumer(queue);
        }
        return null;
    }
}
