package org.sber;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public final class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static boolean allStringConstantValuesEqualsTheirNames(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (field.getType().equals(String.class)
                    && Modifier.isPublic(modifiers)
                    && Modifier.isStatic(modifiers)
                    && Modifier.isFinal(modifiers)
            ) {
                try {
                    if (!Objects.equals(field.getName(), field.get(null)))
                        return false;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
}
