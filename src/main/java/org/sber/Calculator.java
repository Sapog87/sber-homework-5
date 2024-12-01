package org.sber;

import org.sber.annotation.Cache;
import org.sber.annotation.Metric;

import java.math.BigInteger;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number
     */
    @Cache
    @Metric
    BigInteger calc(int number);
}
