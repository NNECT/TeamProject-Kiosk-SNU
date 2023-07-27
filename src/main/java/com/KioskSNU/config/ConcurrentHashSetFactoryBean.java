package com.KioskSNU.config;

import org.springframework.beans.factory.FactoryBean;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSetFactoryBean implements FactoryBean<Set<Integer>> {
    private ConcurrentHashMap<Integer, Boolean> map;

    public ConcurrentHashSetFactoryBean() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Integer> getObject() {
        return map.keySet();
    }

    @Override
    public Class<?> getObjectType() {
        return Set.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
