package com.integration;
import com.integration.gateway.SampleMessageGateway;
import com.integration.mock.SampleMessageMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class IntegrationApplication implements CommandLineRunner {


    @Autowired(required = false)
    private SampleMessageGateway sampleMessageGateway;

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.sampleMessageGateway.send(SampleMessageMock.getMock());
    }


}
