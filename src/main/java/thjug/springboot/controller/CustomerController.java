package thjug.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import thjug.springboot.entity.Customer;

/**
 * <p>
 * If you annotate a method with @ResponseBody,
 * spring will try to convert its return value and write it to the http response automatically.
 * If you annotate a methods parameter with @RequestBody,
 *
 * @author peerapat
 */
@Controller
public class CustomerController {

    final static String PATH = "/Users/pasoktummarungsri/Desktop/";

    final static ObjectMapper mapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping(value = "/customer", method = POST)
    public String create(@RequestParam(value="firstname") final String firstname,
                         @RequestParam(value="lastname") final String lastname) {
        return "success, id:1";
    }

    @ResponseBody
    @RequestMapping(value = "/customer/{id}", method = GET)
    public Customer read(@PathVariable(value="id") final Long id) {
        return new Customer(id, "Coco", "Crunch");
    }

    @ResponseBody
    @RequestMapping(value = "/customer", method = PUT)
    public String update(@RequestBody final Customer customer) throws IOException {
        return "success, id:" + customer.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/customer", method = DELETE)
    public String delete(@RequestParam(value="id") final Long id) {
        return "success, id:" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/customer/upload", method = POST)
    public String upload(@RequestParam("id") final String id,
            @RequestParam("file") final MultipartFile file) {
        if (file.isEmpty()) {
            return "error, file cannot empty";
        }

        try {
            final String name = file.getName();
            final byte[] bytes = file.getBytes();
            try (BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(PATH, name)))) {
                stream.write(bytes);
            }
            return "success, id:" + id + " name:" + name;
        } catch (final Exception e) {
            return "error, " + e.getMessage();
        }

    }

}
