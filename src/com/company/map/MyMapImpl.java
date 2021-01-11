package com.company.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyMapImpl<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private int size;

    private Object[] keys;
    private Object[] values;

    public MyMapImpl() {
        this.keys = new Object[DEFAULT_CAPACITY];
        this.values = new Object[DEFAULT_CAPACITY];
    }

    public MyMapImpl(int initialCapacity) {
        this.keys = new Object[initialCapacity];
        this.values = new Object[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (containsKey(key)) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == null) break;
                if (keys[i].equals(key)) {
                    Object value1 = values[i];
                    values[i] = value;
                    return (V) value1;
                }
            }
        } else {
            this.keys[size] = key;
            this.values[size] = value;
            size++;
        }
        return value;
    }

    @Override
    public V remove(K key) {
        Object old = null;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                old = values[i];
                if (keys.length - 1 - i >= 0) System.arraycopy(keys, i + 1, keys, i, keys.length - 1 - i);
                if (values.length - 1 - i >= 0) System.arraycopy(values, i + 1, values, i, values.length - 1 - i);
                size--;
                break;
            }
        }
        return (V) old;
    }

    @Override
    public V get(K key) {
        Object value = null;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) break;
            if (keys[i].equals(key)) {
                value = values[i];
            }
        }
        return (V) value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) break;
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) break;
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MyMap.Entry<K, V>> entries() {
        List<MyMap.Entry<K, V>> entries = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Entry<K, V> objectObjectEntry = new Entry<>((K) keys[i], (V) values[i]);
            entries.add(objectObjectEntry);
        }

        return entries;
    }

    @Override
    public Object[] values() {
        int valSize = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) break;
            if (values[i] != null) {
                valSize++;
            }
        }
        return Arrays.copyOf(values, valSize);
    }

    @Override
    public Object[] keys() {
        int keySize = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) break;
            if (keys[i] != null) {
                keySize++;
            }
        }
        return Arrays.copyOf(keys, keySize);
    }

    @Override
    public String toString() {
        return "MyMapImpl{" +
                "size=" + size +
                ", keys=" + Arrays.toString(keys) +
                ", values=" + Arrays.toString(values) +
                '}';
    }

    private static class Entry<K, V> implements MyMap.Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
