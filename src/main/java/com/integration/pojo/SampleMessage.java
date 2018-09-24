package com.integration.pojo;

import java.util.List;

public class SampleMessage {

    private String title;

    private String description;

    public List<SampleElement> sampleElements;

    public SampleMessage(){ }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SampleElement> getSampleElements() {
        return sampleElements;
    }

    public void setSampleElements(List<SampleElement> sampleElements) {
        this.sampleElements = sampleElements;
    }
}
