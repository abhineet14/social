package com.abhineet.social.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public abstract class Consumer implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(FriendShipConsumer.class);
    private Queue<String> consumerQueue;

    public Consumer(Queue<String> consumerQueue) {
        this.consumerQueue=consumerQueue;
    }

    public void run() {
        logger.info("consumer started");
        for (; ; ) {
            synchronized (consumerQueue) {
                if (consumerQueue.size() == 0) {
                    try {
                        consumerQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String message = (String) consumerQueue.poll();
                processMessage(message);
                consumerQueue.notify();
            }
        }
    }
    public abstract void processMessage(String message);
}
