package com.integration;
import com.integration.pojo.GatewayMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class IntegrationApplication {

    @MessagingGateway(defaultRequestChannel = "messageChannel")
    public interface Gateway {
        public void send(GatewayMessage gatewayMessage);
    }

    @Bean
    public MessageChannel messageChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "messageChannel")
    public MessageHandler messageHandler() {
        return (message)->{
          System.out.println(((GatewayMessage)message.getPayload()).getDescription());
        };
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext= SpringApplication.run(IntegrationApplication.class, args);
      Gateway gateway= configurableApplicationContext.getBean(Gateway.class);

        gateway.send(getMessage());
    }


    private static GatewayMessage getMessage(){

        GatewayMessage gatewayMessage = new GatewayMessage();

        gatewayMessage.setTitle("call Spring integration gateway");

        gatewayMessage.setDescription(
                "Step 1) call Gateway whit default request channel whit name : 'messageChannel' defined in GatewayChannel Class \n" +
                        "Step 2) redirect to GatewayServiceActivator and call messageHandler method whit message sended in the step 1 ");

        return gatewayMessage;
    }

}
