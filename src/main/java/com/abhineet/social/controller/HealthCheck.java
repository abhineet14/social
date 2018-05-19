package com.abhineet.social.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheck {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

    @RequestMapping(path = "healthCheck")
    public @ResponseBody  String getHealth(){
        logger.info("heathCheck is called");
        return "every thing is fine";
    }
}
