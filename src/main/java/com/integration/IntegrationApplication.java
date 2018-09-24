package com.integration;
import com.integration.mock.SampleMessageMock;
import com.integration.pojo.SampleMessage;
import com.integration.service.SampleMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.splitter.MethodInvokingSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;


@SpringBootApplication
public class IntegrationApplication {

    public static void main(String[] args) {
     ConfigurableApplicationContext ctx= SpringApplication.run(IntegrationApplication.class, args);
     SampleMessageGateway sampleMessageGateway= ctx.getBean(SampleMessageGateway.class);
     sampleMessageGateway.send(SampleMessageMock.getMock());
    }


    @MessagingGateway(defaultRequestChannel = "defaultRequestChannel")
    public interface SampleMessageGateway {
        public void send(SampleMessage sampleMessage);
    }

    @Bean
    public MessageChannel defaultRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        DirectChannel directChannel=new DirectChannel();
        directChannel.setBeanName("outputChannel");
        return directChannel;
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler messageHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
            System.out.println(message);
            }
        };
    }


    @Bean
    public SampleMessageService sampleMessageServiceBean() {
        return new SampleMessageService();
    }

    @Bean
    @Splitter(inputChannel = "defaultRequestChannel")
    public AbstractMessageSplitter splitter() {

        MethodInvokingSplitter methodInvokingSplitter = new MethodInvokingSplitter(sampleMessageServiceBean(), "calledBySplitter");

        methodInvokingSplitter.setOutputChannel(outputChannel());

        return methodInvokingSplitter;
    }


}
