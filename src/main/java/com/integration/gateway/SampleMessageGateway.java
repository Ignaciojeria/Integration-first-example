package com.integration.gateway;

import com.integration.pojo.SampleMessage;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputchannel")
public interface SampleMessageGateway {

    public void send(SampleMessage sampleMessage);

}
