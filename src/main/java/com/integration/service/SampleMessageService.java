package com.integration.service;

import com.integration.pojo.SampleMessage;
import org.springframework.stereotype.Service;

@Service
public class SampleMessageService {

    public SampleMessage getMessage(SampleMessage sampleMessage){
        return sampleMessage;
    }
}
