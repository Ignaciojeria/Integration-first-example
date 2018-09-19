package com.integration.channel;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

public class SampleMessageChannel {

    @Bean
    public MessageChannel outputchannel() {
        return new DirectChannel();
    }
}
