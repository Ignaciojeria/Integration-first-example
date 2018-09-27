package com.integration.service;
import com.integration.pojo.SampleElement;
import com.integration.pojo.SampleMessage;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.Collection;


public class SampleMessageServiceActivator {


    public Message<?> calledByServiceAcrivator(Message<?> sampleElement){
        System.out.println(sampleElement);
        return sampleElement;
    }

}
