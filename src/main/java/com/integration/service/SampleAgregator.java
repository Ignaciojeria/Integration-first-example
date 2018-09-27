package com.integration.service;

import org.springframework.messaging.Message;

import java.util.List;

public class SampleAgregator {

    public List<Message<?>> joinMessage(List<Message<?>> messages){
        return messages;
    }

}
