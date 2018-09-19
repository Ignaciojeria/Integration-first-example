package com.integration.mock;

import com.integration.pojo.SampleMessage;

public class SampleMessageMock {

    public static SampleMessage getMock(){

        SampleMessage sampleMessage=new SampleMessage();

        sampleMessage.setDescription("Examaple message mock description");

        sampleMessage.setTitle("Example message mock title");

        return sampleMessage;
    }
}
