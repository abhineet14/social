package com.abhineet.social.controller;

import com.abhineet.social.helper.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Queue;

@Controller
public class HealthCheck {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

    @Autowired
    Producer producer;
    @Autowired
    Queue<String> friendShipQueue;
    @RequestMapping(path = "healthCheck")
    public @ResponseBody  String getHealth(){
        logger.info("heathCheck is called");
        return "every thing is fine";
    }
    @RequestMapping(path = "produce")
    public @ResponseBody String produce(){
        producer.produce("hello",friendShipQueue );
        return "ok";

    }
}
