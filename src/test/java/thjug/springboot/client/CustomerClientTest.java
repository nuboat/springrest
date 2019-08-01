package thjug.springboot.client;

import org.junit.Ignore;
import org.junit.Test;
import thjug.springboot.entity.Customer;

@Ignore
public class CustomerClientTest {

    @Test
    public void testRead() throws Exception {
        final CustomerClient client = new CustomerClient();
        final Customer c = client.read(1L);

        assert c.getId() == 1 : "Id should be 1";
    }

    @Test
    public void testCreate() throws Exception {
        final CustomerClient client = new CustomerClient();
        client.create("John", "T");
    }

    @Test
    public void testUpdate() throws Exception {
        final CustomerClient client = new CustomerClient();
        final Customer c = client.read(1L);

        assert c.getId() == 1 : "Id should be 1";
    }

    @Test
    public void testDelete() throws Exception {
        final CustomerClient client = new CustomerClient();
        final Customer c = client.read(1L);

        assert c.getId() == 1 : "Id should be 1";
    }

}
