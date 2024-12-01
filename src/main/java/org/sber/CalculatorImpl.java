package org.sber;

import java.math.BigInteger;

public class CalculatorImpl implements Calculator {
    @Override
    public BigInteger calc(int number) {
        if (number < 0)
            throw new IllegalArgumentException("Число не может быть отрицательным");

        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= number; i++)
            factorial = factorial.multiply(BigInteger.valueOf(i));

        return factorial;
    }
}
