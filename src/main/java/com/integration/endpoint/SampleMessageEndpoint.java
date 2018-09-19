package com.integration.endpoint;
import com.integration.service.SampleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class SampleMessageEndpoint {

    @Autowired
    private SampleMessageService sampleMessageService;


    @Bean
    @ServiceActivator(inputChannel = "outputchannel")
    public MessageHandler  messageHandler(){
        return (message)->{
            System.out.println(message);
        };
    }


}
