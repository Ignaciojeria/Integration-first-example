package com.integration.mock;

import com.integration.pojo.SampleElement;
import com.integration.pojo.SampleMessage;

import java.util.ArrayList;
import java.util.List;

public class SampleMessageMock {

    public static SampleMessage getMock(){

        SampleMessage sampleMessage=new SampleMessage();

        sampleMessage.setDescription("Examaple message mock description");

        sampleMessage.setTitle("Example message mock title");

        List<SampleElement> sampleElementList=new ArrayList<>();

        SampleElement sampleElementA=new SampleElement();
        SampleElement sampleElementB=new SampleElement();
        SampleElement sampleElementC=new SampleElement();

        sampleElementA.setValue(1);
        sampleElementA.setDescription("sample element a description");

        sampleElementB.setValue(2);
        sampleElementB.setDescription("sample element b description");

        sampleElementC.setValue(3);
        sampleElementC.setDescription("sample element c description");

        sampleElementList.add(sampleElementA);
        sampleElementList.add(sampleElementB);
        sampleElementList.add(sampleElementC);

        sampleMessage.setSampleElements(sampleElementList);

        return sampleMessage;
    }
}
