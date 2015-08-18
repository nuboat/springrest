package thjug.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * If you annotate a method with @ResponseBody,
 * spring will try to convert its return value and write it to the http response automatically.
 * If you annotate a methods parameter with @RequestBody,
 *
 * @author peerapat
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World !";
    }
}
