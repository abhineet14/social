package com.abhineet.social.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Queue;
@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public void produce(String message,Queue<String> queue){
        synchronized (queue) {
            logger.info("going to add the message = {} in queue", message);
            queue.add(message);
            logger.info("message = {} added", message);
            queue.notify();
        }
    }

}
