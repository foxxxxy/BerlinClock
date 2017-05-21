package com.ubs.opsit.interviews.tests;

import com.ubs.opsit.interviews.controller.dto.BerlinClockTimeDTO;
import com.ubs.opsit.interviews.controller.dto.TimeDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author foxy
 * @since 21.05.17.
 */
public class BerlinClockControllerTest extends BaseSpringTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters).filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void convertTimeSuccessful() throws Exception {
        final BerlinClockTimeDTO timeDTO = new BerlinClockTimeDTO("O\nRRRR\nOOOO\nYYRYYROOOOO\nYYYO");
        mockMvc.perform(post("/api/convert")
                .content(this.json(new TimeDTO("20:33:45")))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.berlinClockTime", is(timeDTO.getBerlinClockTime())))
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void convertTimeEndPointNotAllowed() throws Exception {
        mockMvc.perform(get("/api/convert")
                .content(this.json(new TimeDTO("20:33:45")))
                .contentType(contentType))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void processMethodNotSupported() throws Exception {
        final String description = "Time: not valid data is not valid";
        final String message = "error.validation";
        mockMvc.perform(post("/api/convert")
                .content(this.json(new TimeDTO("not valid data")))
                .contentType(contentType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.message", is(message)));
    }

    @Test
    public void convertTimeEndPointNotFound() throws Exception {
        mockMvc.perform(post("/api")
                .content(this.json(new TimeDTO("21:09:05")))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
