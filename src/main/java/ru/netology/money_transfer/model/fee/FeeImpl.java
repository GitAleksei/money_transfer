package ru.netology.money_transfer.model.fee;

import ru.netology.money_transfer.model.Amount;

public class FeeImpl implements Fee<Amount> {
    public static final double FEE_PERCENT = 0.01;

    @Override
    public Amount calculate(Amount amount) {
        return new Amount((int) (amount.getValue() * FEE_PERCENT), amount.getCurrency());
    }
}
