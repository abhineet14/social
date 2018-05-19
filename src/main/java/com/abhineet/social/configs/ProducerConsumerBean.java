package com.abhineet.social.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ProducerConsumerBean {
    @Bean
    public Map<String, Queue<String>> consumerQueues(){
        Map<String, Queue<String>> consumerQueues= new HashMap<>();
        consumerQueues.put("friendShipQueue",friendShipQueue());
        return consumerQueues;
    }
    @Bean
    public Queue<String> friendShipQueue(){
        return new LinkedList<>();
    }

}
