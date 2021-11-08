package nice.com.solution.controller;

import nice.com.solution.service.WealthService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class WealthControllerTest {

    @Mock
    WealthService wealthService;

    @Autowired
    WealthController wealthController;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testWealthRating() throws Exception {

        String requestBody =
                "{  \n" +
                        " \"id\": 123456789,  \n" +
                        " \"personalInfo\": {  \n" +
                        " \"firstName\": \"Bill\",  \n" +
                        " \"lastName\": \"Gates\",  \n" +
                        " \"city\": \"Washington\"  \n" +
                        " },  \n" +
                        " \"financialInfo\": {  \n" +
                        " \"cash\": 16000000000,  \n" +
                        " \"numberOfAssets\": 50  \n" +
                        " }  \n" +
                        "} \n";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);


        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<String>(requestBody, requestHeaders);

        //Invoking the API
        String apiResponse =
                restTemplate.postForObject("http://localhost:8888/api//wealth-rating", httpEntity, String.class, Collections.EMPTY_MAP);

        //Check result
        assertNotNull(apiResponse);


    }

}