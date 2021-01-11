package com.company.map;

import org.omg.CORBA.Environment;

import java.util.*;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;
    private int size;

    private Entry<K, V>[] entries;


    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
        this.entries = new Node[DEFAULT_CAPACITY];
    }

    public MyHashMap(int initCapacity) {
        this.table = new Node[initCapacity];
        this.entries = new Node[initCapacity];
    }

    public static void main(String[] args) {
        Map<String, String> strings = new HashMap<>();
        strings.put(null, "Hello 0");
        strings.put("1", "Hello 1");
        strings.put("2", "Hello 2");
        strings.put("3", "Hello 3");

        System.out.println(strings);

        strings.put(null, "hello 00");

        System.out.println(strings);


//        MyHashMap<String, String> stringStringMyHashMap = new MyHashMap<>();
//        stringStringMyHashMap.put("1", "Hello 1");
//        stringStringMyHashMap.put("2", "Hello 2");
//        stringStringMyHashMap.put("3", "Hello 3");
//        stringStringMyHashMap.put("4", "Hello 4");
//        stringStringMyHashMap.put("5", "Hello 5");
//        stringStringMyHashMap.put("6", "Hello 5");
//        stringStringMyHashMap.put("7", "Hello 5");
//        stringStringMyHashMap.put("8", "Hello 8");
//        stringStringMyHashMap.put("9", "Hello 5");
//        stringStringMyHashMap.put("10", "Hello 5");
//        stringStringMyHashMap.put("11", "Hello 5");
//        stringStringMyHashMap.put("12", "Hello 5");
//        stringStringMyHashMap.put("13", "Hello 5");
//        stringStringMyHashMap.put("14", "Hello 5");
//        stringStringMyHashMap.put("15", "Hello 5");

//        System.out.println(stringStringMyHashMap.remove("8"));

//        System.out.println(stringStringMyHashMap.containsKey("3"));
//        System.out.println(stringStringMyHashMap.containsValue("Hello "));

//        System.out.println(Arrays.toString(stringStringMyHashMap.values()));
//        System.out.println(Arrays.toString(stringStringMyHashMap.keys()));

//        System.out.println(stringStringMyHashMap.entries());

//        System.out.println(stringStringMyHashMap);
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private int getIndex(int hash) {
        return Math.abs(hash % this.table.length);
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(hash(key));
        if (table[index] == null) {
            Node<K, V> kvNode = new Node<>(hash(key), null, key, value);
            table[index] = kvNode;
            entries[size++] = kvNode;
            return value;
        } else {
            Node<K, V> kvNode = table[index];
            if (kvNode.getKey().equals(key)){
                V old = table[index].getValue();
                table[index].setValue(value);
                return old;
            }
            if (kvNode.next == null) {
                Node<K, V> next = new Node<>(hash(key), null, key, value);
                kvNode.next = next;
                entries[size++] = next;
                return value;
            } else {
                Node<K, V> temp = kvNode.next;
                while (true){
                    if (kvNode.getKey().equals(key)){
                        V old = table[index].getValue();
                        table[index].setValue(value);
                        return old;
                    }
                    if (temp.next == null){
                        Node<K, V> next = new Node<>(hash(key), null, key, value);
                        temp.next = next;
                        entries[size++] = next;
                        return value;
                    }
                    temp = temp.next;
                }
            }
        }
    }

    @Override
    public V remove(K key) {
        int index = getIndex(hash(key));
        if (table[index] == null) return null;
        if(table[index].getKey().equals(key)){
            V old = table[index].getValue();
            table[index] = null;
            size--;

            for (int i = 0; i < entries.length; i++) {
                if (entries[i].getKey().equals(key)){
                    for (int i1 = i; i1 < entries.length - 1; i1++) {
                        entries[i1] = entries[i1 + 1];
                    }
                    break;
                }
            }

            return old;
        }else {
            Node<K,V> temp = table[index].next;
            while (true){
                if (temp == null) return null;
                if (temp.next.getKey().equals(key)){
                    Node<K, V> next = temp.next.next;
                    V old = temp.next.value;
                    temp.next.value = null;
                    temp.next.key = null;
                    temp.next = next;
                    size--;

                    for (int i = 0; i < entries.length; i++) {
                        if (entries[i].getKey().equals(key)){
                            for (int i1 = i; i1 < entries.length - 1; i1++) {
                                entries[i1] = entries[i1 + 1];
                            }
                            break;
                        }
                    }


                    return old;
                }
                temp = temp.next;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = getIndex(hash(key));
        if (table[index] == null) return null;
        if(table[index].getKey().equals(key)){
            return table[index].getValue();
        }else {
            Node<K,V> temp = table[index].next;
            while (true){
                if (temp == null) return null;
                if (temp.getKey().equals(key)){
                    return temp.getValue();
                }
                temp = temp.next;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) break;
            if (entries[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) break;
            if (entries[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Entry<K, V>> entries() {
        return Arrays.asList(Arrays.copyOf(entries, size));
    }

    @Override
    public Object[] values() {
        Object[] array = new Object[size];
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) break;
            V value = entries[i].getValue();
            array[i] = value;
        }
        return array;
    }

    @Override
    public Object[] keys() {
        Object[] array = new Object[size];
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) break;
            K key = entries[i].getKey();
            array[i] = key;
        }
        return array;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "table=" + Arrays.toString(table) +
                ", size=" + size +
                '}';
    }

    private static class Node<K, V> implements MyMap.Entry<K, V> {
        int hash;
        Node<K, V> next;
        K key;
        V value;

        public Node(int hash, Node<K, V> next, K key, V value) {
            this.hash = hash;
            this.next = next;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", next=" + next +
                    ", key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
