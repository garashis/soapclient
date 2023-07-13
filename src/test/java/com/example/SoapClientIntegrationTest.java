package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.*;
import okio.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.io.*;

import static com.example.SoapClientIntegrationTest.TestConfig;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SoapclientApplication.class, TestConfig.class})
class SoapClientIntegrationTest {
    public static MockWebServer mockServer;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("classpath:data.xml")
    Resource resource;

    @BeforeAll
    static void beforeAll() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start(8099);
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockServer.shutdown();
    }

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        mockBackendEndpoint(200, "responseBody");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/add/1/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberToWordsResult", is("one hundred")));
        RecordedRequest req = mockServer.takeRequest();
        System.out.println(">>>>>>>>>>>>>>>>>   " +req.getRequestUrl());
        System.out.println(">>>>>>>>>>>>>>>>>   " +req.getBody().readUtf8());
    }

    private void mockBackendEndpoint(int responseCode, String body) throws IOException {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(responseCode)
                .setBody(fileToBytes(resource.getFile()))
                .addHeader("Content-Type", "text/xml");
        mockServer.enqueue(mockResponse);
    }

    private Buffer fileToBytes(File file) throws IOException {
        Buffer result = new Buffer();
        result.writeAll(Okio.source(file));
        return result;
    }

    @Configuration
    public static class TestConfig {
        @Bean(name = "wokeWebClient")
        WebServiceTemplate dmppsWebClient() {
            HttpUrl url = mockServer.url("/");
            WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
            webServiceTemplate.setDefaultUri(url.toString());
            return webServiceTemplate;
        }
    }
}