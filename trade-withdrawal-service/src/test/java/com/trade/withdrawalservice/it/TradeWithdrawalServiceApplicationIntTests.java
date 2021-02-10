/*
package com.trade.withdrawalservice.it;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.withdrawalservice.TradeWithdrawalServiceApplication;
import com.trade.withdrawalservice.model.Withdrawal;
import com.trade.withdrawalservice.repository.WithdrawalRepository;
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
@SpringBootTest(classes = TradeWithdrawalServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeWithdrawalServiceApplicationIntTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    private static final Withdrawal WITHDRAWAL = new Withdrawal(0L,1L,1L,"cratetime","execute time",500L,"sbi","6666",1,"remark","type");

    private String fullURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Test
    public void createWithdrawal() throws JsonProcessingException, JSONException {

        String createUserUri = "/trade/withdrawal/create";
        HttpEntity<Withdrawal> entity = new HttpEntity<Withdrawal>(WITHDRAWAL, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((4001), jsonObject.get("statusCode"));

    }

    @Test
    public void getWithdrawal() throws Exception {

        String getUserUri = "/trade/withdrawal/getWithdrawal/1";   //    @GetMapping("/getWithdrawal/{withdrawalId}")

        HttpEntity<Withdrawal> entity = new HttpEntity<Withdrawal>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(getUserUri), HttpMethod.GET, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((4003), jsonObject.get("statusCode"));
    }

    @Test
    public void getAllWithdrawalList() throws JSONException {
        HttpHeaders header = new HttpHeaders();
        String uriToCreateUser = "/trade/withdrawal/withdrawals";
        HttpEntity<Withdrawal> userEntity = new HttpEntity<Withdrawal>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(uriToCreateUser), HttpMethod.GET, userEntity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((4005), jsonObject.get("statusCode"));
    }

    */
/*@Test
    public void updateWithdrawal() throws JsonProcessingException {
        String URI = "/withdrawal/create/";

        HttpEntity<Withdrawal> entity = new HttpEntity<>(withdrawal, headers);
        testRestTemplate.exchange(fullURLWithPort(URI), HttpMethod.POST, entity, String.class);

        String URI1 = "/withdrawal/update";
        Withdrawal withdrawal1 = new Withdrawal(2L, 2L, "17-7-2020", "01:41:55", 10000, "accName", 675, "bankName", "686652871", 1, "remarks");
        String inputInJson1 = this.mapToJson(withdrawal1);

        HttpEntity<Withdrawal> entity1 = new HttpEntity<>(withdrawal1, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(URI1), HttpMethod.PUT, entity1, String.class);
        String responseInJson = response.getBody();
        assertEquals(responseInJson, inputInJson1);
        withdrawalRepository.deleteById(withdrawal.getWithdrawalId());
    }

    @Test
    public void deleteWithdrawal() {
        withdrawalRepository.deleteById(withdrawal.getWithdrawalId());
    }*//*


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
*/
