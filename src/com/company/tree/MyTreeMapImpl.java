package com.company.tree;

import java.util.*;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {
    private Entry<K, V> root;
    private int size;
    private Comparator<K> comparator;
    private List<Map.Entry<K, V>> entryList = new ArrayList<>();

    public static void main(String[] args) {
        MyTreeMapImpl<String, String> stringStringMyTreeMap = new MyTreeMapImpl<>(String::compareTo);

        stringStringMyTreeMap.put("1", "Hello 1");
        stringStringMyTreeMap.put("2", "Hello 2");
        stringStringMyTreeMap.put("3", "Hello 3");
        stringStringMyTreeMap.put("4", "Hello 4");
        stringStringMyTreeMap.put("5", "Hello 5");
        stringStringMyTreeMap.put("5", "Hello 6");

        System.out.println(stringStringMyTreeMap.get("3"));
    }

    public MyTreeMapImpl(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V put(K key, V value) {
        if (isEmpty()) {
            Entry<K, V> root = new Entry<>(key, value, null);
            this.root = root;
            entryList.add(root);
            size++;
        } else {
            Entry<K, V> temp = this.root;
            while (true) {
                if (comparator.compare(key, temp.getKey()) == 0) {
                    V old = temp.getValue();
                    temp.setValue(value);
                    return old;
                }
                if (comparator.compare(key, temp.getKey()) > 0) {
                    if (temp.right == null) {
                        Entry<K, V> right = new Entry<>(key, value, temp);
                        temp.right = right;
                        entryList.add(right);
                        size++;
                        return value;
                    } else {
                        temp = temp.right;
                    }
                }
                if ((comparator.compare(key, temp.getKey()) < 0)) {
                    if (temp.left == null) {
                        Entry<K, V> left = new Entry<>(key, value, temp);
                        temp.left = left;
                        entryList.add(left);
                        size++;
                        return value;
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }
        return value;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> temp = this.root;
        while (true) {
            if (comparator.compare(key, temp.getKey()) == 0) {
                entryList.remove(new Entry<>(key, null, null));
                V old = temp.getValue();
//                temp.parent.right = temp.right;
//                temp.parent.left = temp.left;
                size--;
                return old;
            }
        }

    }

    @Override
    public V get(K key) {
        Entry<K, V> temp = this.root;
        while (true) {
            if (comparator.compare(key, temp.getKey()) == 0) {
                return temp.getValue();
            }
            if (comparator.compare(key, temp.getKey()) > 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    break;
                }
            }
            if (comparator.compare(key, temp.getKey()) < 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        for (Map.Entry<K, V> kvEntry : entryList) {
            if (kvEntry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (Map.Entry<K, V> kvEntry : entryList) {
            if(kvEntry.getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Map.Entry<K, V>> entries() {
        return new ArrayList<>(entryList);
    }

    @Override
    public Object[] values() {
        List<V> vList = new ArrayList<>();
        for (Map.Entry<K, V> kvEntry : entryList) {
            V value = kvEntry.getValue();
            vList.add(value);
        }
        return vList.toArray();
    }

    @Override
    public Object[] keys() {
        List<K> kList = new ArrayList<>();
        for (Map.Entry<K, V> kvEntry : entryList) {
            K key = kvEntry.getKey();
            kList.add(key);
        }
        return kList.toArray();
    }

    @Override
    public String toString() {
        return "MyTreeMapImpl{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
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
            V value1 = this.value;
            this.value = value;
            return value1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
