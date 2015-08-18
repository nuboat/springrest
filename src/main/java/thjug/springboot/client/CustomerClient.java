package thjug.springboot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import thjug.springboot.entity.Customer;

@Slf4j
public class CustomerClient {

    private static final String host = "http://localhost:8080";

    private static final ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

    public Customer read(final Long id) throws IOException {
        final String c = restTemplate
                .getForObject(host + "/customer/"+id, String.class);

        log.info("Raw: {}", c);

        return mapper.readValue(c, Customer.class);
    }

}
