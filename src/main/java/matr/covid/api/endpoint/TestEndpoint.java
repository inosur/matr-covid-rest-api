package matr.covid.api.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osvaldo
 */
@RestController
public class TestEndpoint {

    @GetMapping("test")
    public String doTest() {
        return "hello world";
    }

}
