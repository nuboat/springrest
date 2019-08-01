package thjug.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@ContextConfiguration(classes = MockServletContext.class)
public class CustomerControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new CustomerController()).build();
    }

    @Test
    public void testCreate() throws Exception {
        final MockHttpServletRequestBuilder builder
                = MockMvcRequestBuilders.post("/customer")
                .param("firstname", "Coco")
                .param("lastname", "Crunch");

        final ResultActions result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().string(is("success, id:1")));

        log.info(result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testRead() throws Exception {
        final MockHttpServletRequestBuilder builder
                = MockMvcRequestBuilders.get("/customer/1");

        final ResultActions result = mvc.perform(builder)
                .andExpect(status().isOk());

        assert result.andReturn().getResponse().getContentAsString() != null;

        log.info(result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        final String id = "1";
        final MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.put("/customer")
                .content("{\"id\":1,\"firstName\":\"Coco\",\"lastName\":\"Crunch\"}")
                .contentType(MediaType.APPLICATION_JSON);

        final ResultActions result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().string(is("success, id:" + id)));

        log.info(result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        final String id = "1";
        final MockHttpServletRequestBuilder builder
                = MockMvcRequestBuilders.delete("/customer")
                .param("id", "1");

        final ResultActions result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().string(is("success, id:" + id)));

        log.info(result.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testUpload() throws Exception {
        final FileInputStream fis = new FileInputStream("./files/NB.jpg");

        final MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/customer/upload")
                    .file(new MockMultipartFile("file", fis));
        builder.param("id", "1");

        final ResultActions result = mvc.perform(builder)
                .andExpect(status().isOk());

        log.info(result.andReturn().getResponse().getContentAsString());
    }

}
