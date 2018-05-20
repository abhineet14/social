package com.abhineet.social.helper;

import com.abhineet.social.Service.FriendShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Queue;


public class FriendShipConsumer extends Consumer {

    private static final Logger logger = LoggerFactory.getLogger(FriendShipConsumer.class);

    private ApplicationContext applicationContext;
    public FriendShipConsumer(Queue<String> consumerQueue, ApplicationContext applicationContext) {
        super(consumerQueue);
        this.applicationContext=applicationContext;
    }

    @Override
    public void processMessage(String message) {
        logger.info("message received in friendship consumer= {}", message);
        String[] parseMessage= message.split(":");
        if(parseMessage!=null && parseMessage.length==2){
            FriendShipService friendShipService = (FriendShipService) applicationContext.getBean("friendShipService");
            friendShipService.addMutualFriends(parseMessage[0], parseMessage[1]);
        }
    }
}