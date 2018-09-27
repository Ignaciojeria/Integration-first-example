package com.integration.gateway;


import com.integration.pojo.SampleMessage;

public interface Gateway {
    void send(SampleMessage sampleMessage);
}
