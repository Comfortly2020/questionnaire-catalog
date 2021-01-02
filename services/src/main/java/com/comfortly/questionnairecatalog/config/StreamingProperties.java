package com.comfortly.questionnairecatalog.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("streaming-properties")
@ApplicationScoped
public class StreamingProperties {

    @ConfigValue(value = "processing-topic", watch = true)
    private String processingTopic;

    public String getProcessingTopic() {
        return processingTopic;
    }

    public void setProcessingTopic(String processingTopic) {
        this.processingTopic = processingTopic;
    }
}
