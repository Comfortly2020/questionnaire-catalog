package com.comfortly.questionnairecatalog.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties.config")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(value = "broken", watch = true)
    private Boolean broken;

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }
}
