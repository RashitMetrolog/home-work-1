package ru.itpark.groovy;

import lombok.var;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import ru.itpark.annotation.Cached;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GroovyCachedAnnotationBPP implements BeanPostProcessor {

    private final Map<Object, Object> cache = new HashMap<>();
    private final Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Cached.class)) {
                map.put(beanName, bean.getClass());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!map.containsKey(beanName)) {
            return bean;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(map.get(beanName));
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (objects.length == 0) {
                    return methodProxy.invoke(bean, objects);
                }
                if (cache.containsKey(objects[0])) {
                    System.out.println("Возращаем данные из кеша...");
                    return cache.get(objects[0]);
                }

                var result = methodProxy.invoke(bean, objects);
                cache.put(objects[0], result);
                return result;
            }
        });
        return enhancer.create();
    }


}
