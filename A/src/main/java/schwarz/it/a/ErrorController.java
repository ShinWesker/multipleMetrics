package schwarz.it.a;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error")
public class ErrorController {

    @GetMapping("/dms")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void triggerDmsError() {
        // Logik oder Nachricht für den DMS Fehler (optional)
        throw new RuntimeException("DMS System error");
    }

    @GetMapping("/wws")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void triggerWwsError() {
        // Logik oder Nachricht für den WWS Fehler (optional)
        throw new RuntimeException("WWS System error");
    }

    @GetMapping("/esb")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void triggerEsbError() {
        // Logik oder Nachricht für den ESB Fehler (optional)
        throw new RuntimeException("ESB System error");
    }
}
