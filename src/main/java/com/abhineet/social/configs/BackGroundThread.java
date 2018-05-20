package com.abhineet.social.configs;

import com.abhineet.social.helper.FriendShipConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BackGroundThread {
    private static final Logger logger = LoggerFactory.getLogger(BackGroundThread.class);
    private ExecutorService executorService;
    @Autowired private Map<String, Queue<String>> consumerQueues;
    @Autowired private ApplicationContext applicationContext;
    @PostConstruct
    public void init(){
        ConsumerFactory consumerFactory= new ConsumerFactory();
        for(Map.Entry<String, Queue<String>> entry: consumerQueues.entrySet()){
            executorService = Executors.newFixedThreadPool(1);
            executorService.execute(consumerFactory.getConsumer(entry.getKey(),entry.getValue(),applicationContext ) );
        }
    }
}
