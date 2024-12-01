package org.sber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class BeanUtils {
    private BeanUtils() {
    }

    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        List<Method> getters = Arrays.stream(from.getClass().getMethods())
                .filter(x -> x.getName().startsWith("get"))
                .toList();
        List<Method> setters = Arrays.stream(to.getClass().getMethods())
                .filter(x -> x.getName().startsWith("set"))
                .toList();

        for (Method getter : getters) {
            Method appropriateSetter = setters.stream()
                    .filter(setter ->
                            Objects.equals(
                                    setter.getName().substring(3),
                                    getter.getName().substring(3)
                            )
                    )
                    .filter(setter -> setter.getParameterCount() == 1)
                    .filter(setter -> setter.getParameterTypes()[0].isAssignableFrom(getter.getReturnType()))
                    .findFirst().orElse(null);

            if (appropriateSetter != null) {
                try {
                    Object value = getter.invoke(from);
                    appropriateSetter.invoke(to, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
