package com.abhineet.social.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class FriendShipConsumer extends Consumer {
    private static final Logger logger = LoggerFactory.getLogger(FriendShipConsumer.class);

    public FriendShipConsumer(Queue<String> consumerQueue) {
        super(consumerQueue);;
    }

    @Override
    public void processMessage(String message) {
        logger.info("message received in freindship consumer= {}", message);
    }
}