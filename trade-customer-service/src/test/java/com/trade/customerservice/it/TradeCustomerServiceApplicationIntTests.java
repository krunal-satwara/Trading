package com.trade.customerservice.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.customerservice.TradeCustomerServiceApplication;
import com.trade.customerservice.model.Customer;
import com.trade.customerservice.repository.CustomerRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeCustomerServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeCustomerServiceApplicationIntTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    private String fullURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static final Customer customer = new Customer(0L, "customme", 9876511L, 1, "test@gmail.com", 1, "password", "address", "unique3", 1, "q1", "a1", "q2", "a2", "q3", "a3", 1);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void createCustomer() throws JSONException {

        String createUserUri = "/trade/customer/create";
        HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("resssssss--- "+responseInJson);
        JSONObject jsonObject = new JSONObject(responseInJson);
        assertEquals((1001), jsonObject.get("statusCode"));
    }

    @Test
    public void getCustomer() throws Exception {

       /* Customer customer = new Customer(3L, "custo", 6511L, 1, "jigar@gmail.com", 1, "pppp", "add", "unique", 1, "qq1", "aa1", "qq2", "aa2", "qq3", "aa3", 1);
        String createUserUri = "/trade/customer/create/";
        HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
        testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);*/
        HttpEntity<Customer> entity = new HttpEntity<Customer>(null, headers);

        String getUserUri = "/trade/customer/getCustomer/unique2";
        ResponseEntity<String> responseBody = testRestTemplate.exchange(fullURLWithPort(getUserUri), HttpMethod.GET, entity, String.class);
        System.out.println("+++++++++++"+ responseBody.getBody());
        JSONObject jsonObject = new JSONObject(responseBody.getBody());
        assertEquals((1010), jsonObject.get("statusCode"));
    }
    @Test
    public void getAllCustomerList() throws JSONException {
        HttpHeaders header = new HttpHeaders();
        String uriToCreateUser = "/trade/customer/getAllCustomer";
        HttpEntity<Customer> userEntity = new HttpEntity<Customer>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(uriToCreateUser), HttpMethod.GET, userEntity, String.class);
        String accountResponse = response.getBody();
        JSONObject jsonObject = new JSONObject(accountResponse);
        assertEquals((1005), jsonObject.get("statusCode"));
    }

    @Test
    public void deleteCustomer() throws JSONException {
        HttpHeaders header = new HttpHeaders();
        String createUserUri = "/trade/customer/delete/1";
        HttpEntity<Customer> userEntity = new HttpEntity<Customer>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.DELETE, userEntity, String.class);
        String accountResponse = response.getBody();
        System.out.println("respomse :- "+accountResponse);
        JSONObject jsonObject = new JSONObject(accountResponse);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((1007), jsonObject.get("statusCode"));
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}