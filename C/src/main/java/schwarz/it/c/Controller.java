package schwarz.it.c;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/getHello")
    public String hello(){
        return "hello from C";
    }
}
