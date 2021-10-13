package ru.netology.money_transfer.model.fee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.netology.money_transfer.model.Amount;

@Component
public class FeeImpl implements Fee<Amount> {
    @Value("${fee.percent}")
    private double feePercent;

    @Override
    public Amount calculate(Amount amount) {
        System.out.println(feePercent);
        return new Amount((int) (amount.getValue() * feePercent), amount.getCurrency());
    }
}
