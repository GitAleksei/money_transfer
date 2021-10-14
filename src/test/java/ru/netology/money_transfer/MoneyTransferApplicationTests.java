package ru.netology.money_transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.money_transfer.model.msg.MsgAnswerException;
import ru.netology.money_transfer.model.msg.MsgConfirmOperation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {
    private static final String OPERATION_ID = "3";
    private static final String CODE = "0001";

    @Autowired
    TestRestTemplate restTemplate;

    public static GenericContainer<?> transfer_container =
            new GenericContainer<>("transfer").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        transfer_container.start();
    }

    @Test
    void responseExceptionTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MsgConfirmOperation msgConfirmOperation = new MsgConfirmOperation();
        msgConfirmOperation.setOperationId(OPERATION_ID);
        msgConfirmOperation.setCode(CODE);

        HttpEntity<MsgConfirmOperation> requestEntity =
                new HttpEntity<>(msgConfirmOperation, headers);

        ResponseEntity<MsgAnswerException> forEntity =
                restTemplate.postForEntity("http://localhost:" +
                        transfer_container.getMappedPort(8080) + "/transfer",
                        requestEntity,
                        MsgAnswerException.class);

        var actual = forEntity.getBody();

        System.out.println(actual);
        String expected = "operationId is not registered";

        Assertions.assertEquals(expected, actual.getMessage());
    }

}
