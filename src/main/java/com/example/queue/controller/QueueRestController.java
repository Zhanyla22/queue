package com.example.queue.controller;

import com.example.queue.producer.MessageSender;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class QueueRestController {

    @Autowired
    private MessageSender messageSender;

    @PostMapping(value = "/send")
    public void sendMessage(@RequestParam("message") String message) throws JMSException {
        messageSender.sendToQueue(message);
    }
}
