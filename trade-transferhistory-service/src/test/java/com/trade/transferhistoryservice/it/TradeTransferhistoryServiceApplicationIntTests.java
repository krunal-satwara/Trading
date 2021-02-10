/*
package com.trade.transferhistoryservice.it;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.transferhistoryservice.TradeTransferhistoryServiceApplication;
import com.trade.transferhistoryservice.model.TransferHistory;
import com.trade.transferhistoryservice.repository.TransferRepository;
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
@SpringBootTest(classes = TradeTransferhistoryServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeTransferhistoryServiceApplicationIntTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    private String fullURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static final TransferHistory transferHistory = new TransferHistory(0L,1L, 1L, 1L, "bachat", 1880L, 1380L, 500L, "note", "createdDate");

    @Autowired
    private TransferRepository transferRepository;

    @Test
    public void createTransferHistory() throws JsonProcessingException, JSONException {

        String createUserUri = "/trade/transferHistory/create";
        HttpEntity<TransferHistory> entity = new HttpEntity<TransferHistory>(transferHistory, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(createUserUri), HttpMethod.POST, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((5003), jsonObject.get("statusCode"));
    }

    @Test
    public void getTransferHistory() throws Exception {

        String getUserUri = "/trade/transferHistory/getTransferHistory/5"; //     @PostMapping("/getTransferHistory/{transferId}")
        HttpEntity<TransferHistory> entity = new HttpEntity<TransferHistory>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(getUserUri), HttpMethod.GET, entity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((5005), jsonObject.get("statusCode"));
    }

    @Test
    public void GetAllTransferHistory() throws JSONException {
        HttpHeaders header = new HttpHeaders();
        String uriToCreateUser = "/trade/transferHistory/transferHistories";
        HttpEntity<TransferHistory> userEntity = new HttpEntity<TransferHistory>(null, header);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(uriToCreateUser), HttpMethod.GET, userEntity, String.class);
        String responseInJson = response.getBody();
        System.out.println("responseInJson :- " + responseInJson);
        JSONObject jsonObject=new JSONObject(responseInJson);
        System.out.println("status code :- " + jsonObject.toString());
        assertEquals((5007), jsonObject.get("statusCode"));
    }

    @Test
    public void updateTransferHistory() throws JsonProcessingException {
        String URI = "/transferHistory/create/";

        HttpEntity<TransferHistory> entity = new HttpEntity<>(transferHistory, headers);
        testRestTemplate.exchange(fullURLWithPort(URI), HttpMethod.POST, entity, String.class);

        String URI1 = "/transferHistory/update";

        HttpEntity<TransferHistory> entity1 = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(fullURLWithPort(URI1), HttpMethod.PUT, entity1, String.class);
        String responseInJson = response.getBody();
    }

    @Test
    public void deleteTransferHistory() {
        transferRepository.deleteById(transferHistory.getTransferId());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
*/
