package org.sber.proxy;

import org.sber.annotation.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetricInvocationHandler implements InvocationHandler {
    private final Object delegate;

    public MetricInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class))
            return invoke(method, args);

        long start = System.nanoTime();
        Object result = invoke(method, args);
        long end = System.nanoTime();
        System.out.printf("Время работы метода: %d (в наносек)\n", end - start);

        return result;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
