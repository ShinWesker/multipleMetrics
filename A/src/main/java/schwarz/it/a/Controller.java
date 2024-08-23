package schwarz.it.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        String responseFromB = restTemplate.getForObject("http://service-b:8081/getHello", String.class);
        return "hello from A, " + responseFromB;
    }
}
