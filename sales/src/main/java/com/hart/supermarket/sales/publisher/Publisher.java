package com.hart.supermarket.sales.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {

    @Autowired
    private RedisTemplate template;
    @Autowired
    private ChannelTopic topic;

    @GetMapping("/send")
    public String SendMessage() {
        
        String sendThisMessage = "This is our message";

        template.convertAndSend(topic.getTopic(), sendThisMessage);


        return "Event Published. Check Logz";
    }

}
