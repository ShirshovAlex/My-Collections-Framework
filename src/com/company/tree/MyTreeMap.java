package com.company.tree;

import java.util.List;
import java.util.Map;

public interface MyTreeMap<K, V> {
    V put(K key, V value);
    V remove(K key);
    V get(K key);
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    List<Map.Entry<K, V>> entries();
    Object[] values();
    Object[] keys();
}
