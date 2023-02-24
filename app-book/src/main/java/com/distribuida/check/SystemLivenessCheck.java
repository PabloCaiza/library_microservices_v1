package com.distribuida.check;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Map;


@ApplicationScoped
@Liveness
public class SystemLivenessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        Long memoryUsed=memoryMXBean.getHeapMemoryUsage().getUsed();
        Long memoryCapacity=memoryMXBean.getHeapMemoryUsage().getMax();
        return HealthCheckResponse.
                named("Memory Liveness Check")
                .withData("used",memoryUsed)
                .withData("capacity",memoryCapacity)
                .status(memoryUsed<memoryCapacity*0.9)
                .build();
    }
}
