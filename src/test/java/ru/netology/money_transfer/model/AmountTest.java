package ru.netology.money_transfer.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    void incrementTest() {
        Amount actual = new Amount(100, "RUR");
        actual.increment(actual);
        Amount expected = new Amount(200, "RUR");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeTest() {
        Amount amount = new Amount(100, "RUR");
        Amount actual = amount.negative();
        Amount expected = new Amount(-100, "RUR");
        Assertions.assertEquals(expected, actual);
    }
}