package com.trade.accountservice.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.accountservice.AccountTradeServiceApplication;
import com.trade.accountservice.model.Account;
import com.trade.accountservice.repository.AccountRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountTradeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTradeServiceApplicationIntTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    private String fullURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static final Account account = new Account(0L, 1L, 10100L, "accountname", 1, 100000L, 1, "remarks");

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void createAccount() throws JsonProcessingException, JSONException {

        String createUserUri = "/trade/account/create";

        HttpEntity<Account> entity = new HttpEntity<Account>(account, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((2001), jsonObject.get("statusCode"));
    }

    @Test
    public void updateAccount() throws JsonProcessingException, JSONException {
       /* String URI = "/trade/account/update";

        HttpEntity<Account> entity = new HttpEntity<>(account, headers);
        testRestTemplate.exchange(fullURLWithPort(URI), HttpMethod.POST, entity, String.class);*/

        String URI1 = "/trade/account/update";
        Account account1 = new Account(9L, 1L, 101L, "krunal", 1, 100000L, 1, "remarks");
        String inputInJson1 = this.mapToJson(account1);

        HttpEntity<Account> entity1 = new HttpEntity<>(account1, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(URI1), HttpMethod.PUT, entity1, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((2015), jsonObject.get("statusCode"));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        HttpHeaders header = new HttpHeaders();
        String createUserUri = "/trade/account/delete/9";   //    @DeleteMapping("/delete/{accountId}")
        HttpEntity<Account> userEntity = new HttpEntity<Account>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.DELETE, userEntity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((2007), jsonObject.get("statusCode"));

    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
