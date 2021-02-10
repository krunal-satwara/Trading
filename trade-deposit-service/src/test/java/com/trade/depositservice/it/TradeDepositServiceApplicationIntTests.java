/*

package com.trade.depositservice.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.depositservice.TradeDepositServiceApplication;
import com.trade.depositservice.model.Deposit;
import com.trade.depositservice.repository.DepositRespository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeDepositServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeDepositServiceApplicationIntTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    private String fullURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static final Deposit deposit = new Deposit(0L,1L,1L,500L,"saving","createtime","executetime",1,"remark");

    @Autowired
    DepositRespository depositRespository;

    @Test
    public void createDeposit() throws JsonProcessingException, JSONException {

        String createUserUri = "/trade/deposit/create";
        String jsonInput = this.mapToJson(deposit);

        HttpEntity<Deposit> entity = new HttpEntity<Deposit>(deposit, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((3001), jsonObject.get("statusCode"));
    }

    @Test
    public void getDeposit() throws Exception {

        String getUserUri = "/trade/deposit/getDeposite/3";
        HttpEntity<Deposit> entity = new HttpEntity<Deposit>(null, headers);
        ResponseEntity<String> responce = testRestTemplate.exchange(fullURLWithPort(getUserUri), HttpMethod.GET, entity, String.class);
        String responseInJson = responce.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((3003), jsonObject.get("statusCode"));
    }

    @Test
    public void getAllDeposit() throws JSONException {
        HttpHeaders header = new HttpHeaders();
        String uriToCreateUser = "/trade/deposit/deposits";
        HttpEntity<Deposit> userEntity = new HttpEntity<Deposit>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(uriToCreateUser), HttpMethod.GET, userEntity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((3005), jsonObject.get("statusCode"));
    }

    */
/*@Test
    public void updateAccount() throws JsonProcessingException, JSONException {

        String URI1 = "/trade/deposit/update";
         Deposit deposit1 = new Deposit(3L,1L,1L,500L,"bachat","createtime","executetime",1,"remark");

        HttpEntity<Deposit> entity1 = new HttpEntity<>(deposit1, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(URI1), HttpMethod.PUT, entity1, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((3005), jsonObject.get("statusCode"));
    }

*//*



    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

*/
