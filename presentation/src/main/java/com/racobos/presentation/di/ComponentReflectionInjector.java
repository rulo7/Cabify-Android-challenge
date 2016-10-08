package com.racobos.presentation.di;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by konmik
 * https://github.com/konmik/nucleus/blob/master/nucleus-example-real-life/src/main/java/nucleus/example/util/ComponentReflectionInjector.java
 */
public final class ComponentReflectionInjector<T> {

    private static ConcurrentMap<Class<?>, HashMap<Class<?>, Method>> cache = new ConcurrentHashMap<>();
    private final Class<T> componentClass;
    private final T component;
    private final HashMap<Class<?>, Method> methods;

    public ComponentReflectionInjector(Class<T> componentClass, T component) {
        this.componentClass = componentClass;
        this.component = component;
        this.methods = getMethods(componentClass);
    }

    private static HashMap<Class<?>, Method> getMethods(Class componentClass) {
        HashMap<Class<?>, Method> methods = cache.get(componentClass);
        if (methods == null) {
            synchronized (cache) {
                methods = cache.get(componentClass);
                if (methods == null) {
                    methods = new HashMap<>();
                    for (Method method : componentClass.getMethods()) {
                        Class<?>[] params = method.getParameterTypes();
                        if (params.length == 1) {
                            methods.put(params[0], method);
                        }
                    }
                    cache.put(componentClass, methods);
                }
            }
        }
        return methods;
    }

    public T getComponent() {
        return component;
    }

    public void inject(Object target) {
        Class targetClass = target.getClass();
        Method method = methods.get(targetClass);
        while (method == null && targetClass != null) {
            targetClass = targetClass.getSuperclass();
            method = methods.get(targetClass);
        }
        if (method == null) {
            throw new RuntimeException(
                    String.format("No %s injecting method exists in %s component", target.getClass(), componentClass));
        }
        try {
            method.invoke(component, target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}