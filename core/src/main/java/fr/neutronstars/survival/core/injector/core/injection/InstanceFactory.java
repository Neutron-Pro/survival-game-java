package fr.neutronstars.survival.core.injector.core.injection;

import fr.neutronstars.survival.core.injector.api.exception.InjectorConstructorException;
import fr.neutronstars.survival.core.injector.api.exception.InjectorInvalidClassException;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.injection.provider.Provider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

class InstanceFactory {
    private final Injector injector;

    protected InstanceFactory(Injector injector) {
        this.injector = injector;
    }

    protected <T> T create(Class<T> clazz, Object... params) {
        final int modifiers = clazz.getModifiers();
        if (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers)) {
            throw new InjectorInvalidClassException(
                String.format("The class <%s> is not instantiable!", clazz.getName())
            );
        }

        try {
            constructorLoop:
            for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                final Object[] objects = new Object[constructor.getParameterCount()];
                int index = 0;
                int paramIndex = 0;

                for (final Parameter parameter : constructor.getParameters()) {
                    final Provider<?> provider = this.injector.providers().of(parameter.getType());
                    if (provider != null) {
                        objects[index++] = provider.of();
                        continue;
                    }
                    if (params.length > paramIndex) {
                        Class<?> type = this.typeOf(params[paramIndex].getClass());
                        Class<?> parameterType = this.typeOf(parameter.getType());

                        if (type.equals(parameterType) || parameterType.isAssignableFrom(type)) {
                            objects[index++] = params[paramIndex++];
                            continue;
                        }
                    }

                    continue constructorLoop;
                }

                final boolean accessible = constructor.isAccessible();
                constructor.setAccessible(true);
                final T instance = clazz.cast(constructor.newInstance(objects));
                constructor.setAccessible(accessible);
                return instance;
            }
        } catch (Throwable throwable) {
            throw new InjectorInvalidClassException(throwable.getMessage(), throwable);
        }
        throw new InjectorConstructorException(
            String.format(
                "Constructor of <%s> not found with specified parameters!",
                clazz.getName()
            )
        );
    }

    private Class<?> typeOf(Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        if (clazz == boolean.class) {
            return Boolean.class;
        }
        if (clazz == int.class) {
            return Integer.class;
        }
        if (clazz == double.class) {
            return Double.class;
        }
        if (clazz == long.class) {
            return Long.class;
        }
        if (clazz == float.class) {
            return Float.class;
        }
        if (clazz == byte.class) {
            return Byte.class;
        }
        if (clazz == short.class) {
            return Short.class;
        }
        if (clazz == char.class) {
            return Character.class;
        }
        return clazz;
    }
}
