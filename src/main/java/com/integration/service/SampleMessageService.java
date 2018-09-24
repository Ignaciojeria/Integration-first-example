package com.integration.service;
import com.integration.pojo.SampleMessage;
import java.util.ArrayList;
import java.util.Collection;


public class SampleMessageService {

    public Collection<Object> calledBySplitter(SampleMessage sampleMessage){

        Collection<Object> collection=new ArrayList<>();

        collection.add(sampleMessage);

        collection.addAll(sampleMessage.getSampleElements());

        return collection;
    }
}
