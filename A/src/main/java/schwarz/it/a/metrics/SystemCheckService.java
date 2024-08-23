package schwarz.it.a.metrics;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SystemCheckService {

    private final CustomMetricsConfiguration customMetrics;

    public SystemCheckService(CustomMetricsConfiguration customMetrics) {
        this.customMetrics = customMetrics;
    }

    @Scheduled(fixedRate = 5000)
    public void checkSystemHealth() {
        checkAndSetServiceHealth("dms");
        checkAndSetServiceHealth("wws");
        checkAndSetServiceHealth("esb");
    }

    private void checkAndSetServiceHealth(String serviceName) {
        boolean serviceHealth = performHealthCheck(serviceName);
        customMetrics.setServiceUp(serviceName, serviceHealth);
    }

    private boolean performHealthCheck(String serviceName) {
        // Tatsächliche Healthcheck Logik
        return true;
    }
}
