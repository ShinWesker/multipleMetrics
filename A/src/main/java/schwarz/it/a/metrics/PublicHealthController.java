package schwarz.it.a.metrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicHealthController {

    private final CustomMetricsConfiguration customMetrics;

    public PublicHealthController(CustomMetricsConfiguration customMetrics) {
        this.customMetrics = customMetrics;
    }

    @GetMapping(value = "/status/{serviceName}", produces = "text/plain")
    public String getServiceStatus(@PathVariable String serviceName) {
        double status = customMetrics.isServiceUp(serviceName);

        return "# HELP " + serviceName + "_service_up Indicates if the service is up and running\n" +
                "# TYPE " + serviceName + "_service_up gauge\n" +
                serviceName + "_service_up " + status;
    }
}
