package com.company.map;

import java.util.List;

public interface MyMap<K, V> {
    V put(K key, V value);
    V remove(K key);
    V get(K key);
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    List<MyMap.Entry<K, V>> entries();
    Object[] values();
    Object[] keys();

    interface Entry<K, V>{
        K getKey();
        V getValue();
        V setValue(V value);
    }
}
