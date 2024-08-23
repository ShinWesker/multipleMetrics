package schwarz.it.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AApplication {

    public static void main(String[] args) {
        SpringApplication.run(AApplication.class, args);
    }

}
