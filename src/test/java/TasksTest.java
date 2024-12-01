import org.junit.Test;
import org.sber.*;
import org.sber.proxy.CachedInvocationHandler;
import org.sber.proxy.MetricInvocationHandler;

import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TasksTest {
    @Test
    public void task1() {
        Calculator calculator = new CalculatorImpl();
        assertEquals(calculator.calc(0), BigInteger.valueOf(1));
        assertEquals(calculator.calc(1), BigInteger.valueOf(1));
        assertEquals(calculator.calc(2), BigInteger.valueOf(2));
        assertEquals(calculator.calc(3), BigInteger.valueOf(6));
        assertEquals(calculator.calc(4), BigInteger.valueOf(24));
        assertEquals(calculator.calc(5), BigInteger.valueOf(120));
        assertEquals(calculator.calc(6), BigInteger.valueOf(720));
    }

    @Test
    public void task2() {
        Class<?> c = Child.class;
        while (c != null) {
            Arrays.stream(c.getDeclaredMethods()).forEach(System.out::println);
            c = c.getSuperclass();
        }
    }

    @Test
    public void task3() {
        Class<?> c = Child.class;
        Arrays.stream(c.getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .forEach(System.out::println);
    }

    @Test
    public void task4() {
        assertFalse(ReflectionHelper.allStringConstantValuesEqualsTheirNames(Parent.class));
        assertTrue(ReflectionHelper.allStringConstantValuesEqualsTheirNames(Child.class));
    }

    @Test
    public void task5() {
        Calculator spiedCalculator = spy(new CalculatorImpl());

        Calculator calculatorCachingProxy = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class<?>[]{Calculator.class},
                new CachedInvocationHandler(spiedCalculator)
        );

        BigInteger realResult = calculatorCachingProxy.calc(5);
        // проверяем, что метод был вызван у настоящего калькулятора
        verify(spiedCalculator, times(1)).calc(5);

        BigInteger cachedResult = calculatorCachingProxy.calc(5);
        // проверяем, что при повторном вызове метода с тем же значением
        // метод не был вызван у настоящего калькулятора
        verify(spiedCalculator, times(1)).calc(5);

        assertEquals(realResult, cachedResult);
    }

    @Test
    public void task6() {
        Calculator calculator = new CalculatorImpl();

        Calculator calculatorMetricProxy = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class<?>[]{Calculator.class},
                new MetricInvocationHandler(calculator)
        );

        System.out.println(calculatorMetricProxy.calc(3));
        System.out.println(calculatorMetricProxy.calc(100));
    }

    @Test
    public void task7() {
        FromClass fromClass = new FromClass(1, "from", 1.1);
        ToClass toClass = new ToClass(0, "to", 1.2);

        BeanUtils.assign(toClass, fromClass);

        assertEquals(toClass.getIntField(), 1);
        assertEquals(toClass.getStringField(), "from");
        // проверяем, что значение не изменилось, несмотря на то,
        // что оба поля double
        assertEquals(toClass.getJustField(), 1.2, 1e-6);
    }
}
