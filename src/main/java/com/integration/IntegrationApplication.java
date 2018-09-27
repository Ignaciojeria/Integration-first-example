package com.integration;
import com.integration.gateway.Gateway;
import com.integration.mock.SampleMessageMock;
import com.integration.pojo.SampleMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.aggregator.AggregatingMessageHandler;
import org.springframework.integration.aggregator.DefaultAggregatingMessageGroupProcessor;
import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.aggregator.SimpleSequenceSizeReleaseStrategy;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.MessageProcessor;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.splitter.MethodInvokingSplitter;
import org.springframework.integration.store.MessageGroupFactory;
import org.springframework.integration.store.MessageGroupStore;
import org.springframework.integration.store.SimpleMessageGroupFactory;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;


@SpringBootApplication
public class IntegrationApplication {

    public static void main(String[] args) {
     ConfigurableApplicationContext ctx= SpringApplication.run(IntegrationApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:integration.xml");

        Gateway gateway=(Gateway) context.getBean("gateway");
        gateway.send(SampleMessageMock.getMock());
        /*
        SampleMessageGateway sampleMessageGateway= ctx.getBean(SampleMessageGateway.class);
        sampleMessageGateway.send(SampleMessageMock.getMock());*/
    }
/*

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
    public MessageChannel inputAgregatorChannel() {
        DirectChannel directChannel=new DirectChannel();
        directChannel.setBeanName("inputAgregatorChannel");
        return directChannel;
    }

    @Bean
    public MessageChannel outputAgregatorChannel() {
        DirectChannel directChannel=new DirectChannel();
        directChannel.setBeanName("outputAgregatorChannel");
        return directChannel;
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler messageHandler() {

        ServiceActivatingHandler serviceActivatingHandler= new ServiceActivatingHandler(new MessageProcessor<Object>() {
            @Override
            public Object processMessage(Message<?> message) {
                System.out.println(message);
                return message;
            }
        });
        serviceActivatingHandler.setOutputChannel(inputAgregatorChannel());

        return serviceActivatingHandler;
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


    @Bean
    public ReleaseStrategy releaseStrategy(){
        return new SimpleSequenceSizeReleaseStrategy();
    }

    @Bean
    public MessageGroupFactory messageGroupFactory(){
        SimpleMessageGroupFactory simpleMessageGroupFactory=new SimpleMessageGroupFactory(SimpleMessageGroupFactory.GroupType.BLOCKING_QUEUE);
        return simpleMessageGroupFactory;
    }

    @Bean
    public MessageGroupStore messageGroupStore(){
        SimpleMessageStore simpleMessageStore=new SimpleMessageStore();
        simpleMessageStore.setMessageGroupFactory(messageGroupFactory());
        return simpleMessageStore;
    }

    @Bean
    @ServiceActivator(inputChannel = "inputAgregatorChannel")
    public MessageHandler agregator(){
        AggregatingMessageHandler aggregatingMessageHandler=new AggregatingMessageHandler(new DefaultAggregatingMessageGroupProcessor(),messageGroupStore());
        aggregatingMessageHandler.setOutputChannelName("outputAgregatorChannel");
        aggregatingMessageHandler.setReleaseStrategy(releaseStrategy());
        return aggregatingMessageHandler;
    }



    @Bean
    @ServiceActivator(inputChannel = "outputAgregatorChannel")
    public MessageHandler agregatorMessageHandler() {

        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message);
            }
        };
    }




*/

}
