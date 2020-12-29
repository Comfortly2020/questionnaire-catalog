package com.comfortly.questionnairecatalog.api.v1.health;

import com.comfortly.questionnairecatalog.config.RestProperties;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class BrokenHealthCheck implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {
        if (restProperties.getBroken()) {
            return HealthCheckResponse.down(BrokenHealthCheck.class.getSimpleName());
        } else {
            return HealthCheckResponse.up(BrokenHealthCheck.class.getSimpleName());
        }
    }
}
