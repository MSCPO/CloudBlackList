package cn.etstmc.cloudblacklist.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeParameterMatcher {

    public interface Matcher<T> {
        boolean matches(Object msg);
    }

    public static <T> Matcher<T> find(Object handler, Class<?> handlerType, String typeParamName) {
        Type[] actualTypeArguments = getTypes(handler, handlerType, typeParamName);
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type = actualTypeArguments[i];
            if (type instanceof Class && typeParamName.equals(handlerType.getTypeParameters()[i].getName())) {
                @SuppressWarnings("unchecked")
                final Class<T> typeClass = (Class<T>) type;
                return typeClass::isInstance;
            }
        }

        throw new IllegalArgumentException("Cannot find " + typeParamName + " parameter of " + handlerType.getSimpleName());
    }

    private static Type[] getTypes(Object handler, Class<?> handlerType, String typeParamName) {
        Class<?> currentClass = handler.getClass();
        while (currentClass != Object.class) {
            if (currentClass == handlerType) {
                break;
            }
            currentClass = currentClass.getSuperclass();
        }

        if (currentClass == Object.class) {
            throw new IllegalArgumentException("Cannot find " + typeParamName + " parameter of " + handlerType.getSimpleName());
        }

        Type genericSuperclass = currentClass.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            throw new IllegalArgumentException(handlerType.getSimpleName() + " is not parameterized");
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        return parameterizedType.getActualTypeArguments();
    }
}
