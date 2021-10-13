package ru.netology.money_transfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.money_transfer.model.Amount;
import ru.netology.money_transfer.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    public TransferRepository() {
        cards.put("1111111111111111",
                new Card("1111111111111111", "11/22", "111",
                        new Amount(10_000_00, "RUR"), "0001"));
        cards.put("2222222222222222",
                new Card("2222222222222222", "11/22", "111",
                        new Amount(10_000_00, "RUR"), "0000"));
        cards.put("3333333333333333",
                new Card("3333333333333333", "11/22", "111",
                        new Amount(10_000_00, "RUR"), "0000"));
        cards.put("4444444444444444",
                new Card("4444444444444444", "11/22", "111",
                        new Amount(10_000_00, "RUR"), "0000"));
    }

    public Card getCard(String cardFromNumber) {
        return cards.get(cardFromNumber);
    }

    public boolean containsCard(String number) {
        return cards.containsKey(number);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Card> cardEntity : cards.entrySet()) {
            res.append("\n")
                    .append(cardEntity.getValue());
        }
        return res.toString();
    }
}
