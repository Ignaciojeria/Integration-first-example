package com.integration.service;

import com.integration.pojo.SampleMessage;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.Collection;

public class SampleMessageSplitter extends AbstractMessageSplitter {

    @Override
    protected Object splitMessage(Message<?> message) {
        Collection<Object> collection=new ArrayList<>();
        collection.addAll(((SampleMessage)message.getPayload()).getSampleElements());
        return collection;
    }
}
