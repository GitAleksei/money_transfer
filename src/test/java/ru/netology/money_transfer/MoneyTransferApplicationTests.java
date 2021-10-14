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
import ru.netology.money_transfer.model.msg.MsgAnswer;
import ru.netology.money_transfer.model.msg.MsgTransfer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {
    private static final String CARD_FROM_NUMBER = "1111111111111111";
    private static final String CARD_FROM_CVV = "111";
    private static final String CARD_FROM_VALID_TILL = "11/22";
    private static final String CARD_TO_NUMBER = "2222222222222222";

    @Autowired
    TestRestTemplate restTemplate;

    public static GenericContainer<?> transfer_container =
            new GenericContainer<>("transfer").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        transfer_container.start();
    }

    @Test
    void responseTransferTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MsgTransfer msgTransfer = new MsgTransfer();
        msgTransfer.setCardFromNumber(CARD_FROM_NUMBER);
        msgTransfer.setCardFromCVV(CARD_FROM_CVV);
        msgTransfer.setCardFromValidTill(CARD_FROM_VALID_TILL);
        msgTransfer.setCardToNumber(CARD_TO_NUMBER);

        HttpEntity<MsgTransfer> requestEntity =
                new HttpEntity<>(msgTransfer, headers);

        ResponseEntity<MsgAnswer> forEntity =
                restTemplate.postForEntity("http://localhost:" +
                        transfer_container.getMappedPort(8080) + "/transfer",
                        requestEntity,
                        MsgAnswer.class);

        var actual = forEntity.getBody();

        String expected = "1";

        assert actual != null;
        Assertions.assertEquals(expected, actual.getOperationId());
    }

}
