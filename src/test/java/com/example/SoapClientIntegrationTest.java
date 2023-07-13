package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.*;
import okio.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.*;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes = {SoapclientApplication.class}, initializers = SoapClientIntegrationTest.Initializer.class)
@ContextConfiguration(classes = {SoapclientApplication.class})
class SoapClientIntegrationTest {
    public static MockWebServer mockServer;
    @Autowired

    NumberClient numberClient;
    @Value("classpath:data.xml")
    Resource resource;

//    ApplicationContextInitializer<ConfigurableApplicationContext> initializer = (ConfigurableApplicationContext configurableApplicationContext) -> TestPropertyValues.of(Map.of("ws.client.number", mockServer.url("/").toString())).applyTo(configurableApplicationContext);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        mockServer = new MockWebServer();
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockServer.shutdown();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("ws.client.number", () -> mockServer.url("/").toString());
    }

    @BeforeEach
    public void before() {
        numberClient.setDefaultUri(mockServer.url("/").toString());
    }

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        mockBackendEndpoint(200, "responseBody");
        mockMvc.perform(MockMvcRequestBuilders.get("/add/1/2").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.numberToWordsResult", is("one hundred")));
        RecordedRequest req = mockServer.takeRequest();
        System.out.println(">>>>>>>>>>>>>>>>>   " + req.getRequestUrl());
        System.out.println(">>>>>>>>>>>>>>>>>   " + req.getBody().readUtf8());
    }

    private void mockBackendEndpoint(int responseCode, String body) throws IOException {
        MockResponse mockResponse = new MockResponse().setResponseCode(responseCode).setBody(fileToBytes(resource.getFile())).addHeader("Content-Type", "text/xml");
        mockServer.enqueue(mockResponse);
    }

    private Buffer fileToBytes(File file) throws IOException {
        Buffer result = new Buffer();
        result.writeAll(Okio.source(file));
        return result;
    }

    /*public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            Map<String, String> map = new HashMap<>();
            //map.put("ws.client.number", mockServer.url("/").toString());
            map.put("logging.level.org.springframework", "trace");
            TestPropertyValues.of(map).applyTo(configurableApplicationContext);
        }
    }*/
}