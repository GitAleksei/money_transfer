package ru.netology.money_transfer.model.fee;

public interface Fee<T> {
    T calculate(T t);
}
