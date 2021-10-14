package ru.netology.money_transfer.model.fee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.money_transfer.model.Amount;

@SpringBootTest
class FeeImplTest {

    @Autowired
    Fee<Amount> fee = new FeeImpl();

    @Test
    void calculateTest() {
        Amount input = new Amount(100, "RUR");
        Amount actual = fee.calculate(input);
        Amount expected = new Amount(1, "RUR");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateZeroTest() {
        Amount input = new Amount(0, "RUR");
        Amount actual = fee.calculate(input);
        Amount expected = new Amount(0, "RUR");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateNullTest() {
        Assertions.assertThrows(NullPointerException.class, () -> fee.calculate(null));
    }
}