package schwarz.it.b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getHello")
    public String hello(){
        String responseFromC = restTemplate.getForObject("http://service-c:8082/getHello", String.class);
        return "hello from B, " + responseFromC;
    }
}
