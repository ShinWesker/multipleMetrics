package schwarz.it.a.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class CustomMetricsConfiguration {

    @Autowired
    private MeterRegistry meterRegistry;

    private final ConcurrentMap<String, Double> serviceStatusMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initMetrics() {
        registerServiceGauge("dms");
        registerServiceGauge("wws");
        registerServiceGauge("esb");
    }

    private void registerServiceGauge(String serviceName) {
        serviceStatusMap.put(serviceName, 0.0);
        Gauge.builder(serviceName + "_service_up", serviceStatusMap, map -> map.get(serviceName))
                .description("Indicates if the service " + serviceName + " is up and running")
                .register(meterRegistry);
    }

    public void setServiceUp(String serviceName, boolean isServiceUp) {
        serviceStatusMap.put(serviceName, isServiceUp ? 1.0 : 0.0);
    }

    public double isServiceUp(String serviceName) {
        return serviceStatusMap.getOrDefault(serviceName, 0.0);
    }
}
