package com.company.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private final int cap;
    int size = 0;
    private Object[] objects;

    public static void main(String[] args) {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>();

        arrayQueue.add("1");
        arrayQueue.add("2");
        arrayQueue.add("3");
        arrayQueue.add("4");
        arrayQueue.add("5");
        arrayQueue.add("6");
        arrayQueue.add("7");
        arrayQueue.add("8");
        arrayQueue.add("9");
        arrayQueue.add("10");

        System.out.println(arrayQueue);

        System.out.println(arrayQueue.remove());
//        System.out.println(arrayQueue.remove());
//
        System.out.println(arrayQueue);
//
//        System.out.println(arrayQueue.peek());
//        System.out.println(arrayQueue.peek());
//
//        System.out.println(arrayQueue);
//
//        arrayQueue.add("99");
//        arrayQueue.add("99");
//        arrayQueue.add("99");
//
//        System.out.println(arrayQueue);
//
//        System.out.println(arrayQueue.poll());
//        System.out.println(arrayQueue.poll());
//        System.out.println(arrayQueue.remove());
    }

    public ArrayQueue() {
        this.objects = new Object[DEFAULT_CAPACITY];
        this.cap = DEFAULT_CAPACITY;
    }

    public ArrayQueue(int initialCapacity) {
        this.objects = new Object[initialCapacity];
        this.cap = initialCapacity;
    }

    @Override
    public boolean add(E e) {
        if (size == cap) throw new IllegalStateException();
        objects[size++] = e;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == cap) return false;
        objects[size++] = e;
        return true;
    }

    @Override
    public E remove() {
        if (size == 0) throw new NoSuchElementException();
        Object object = objects[0];
        for (int i = 0; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        if (size == cap) objects[objects.length - 1] = null;
        size--;
        return (E) object;
    }

    @Override
    public E poll() {
        if (size == 0) return null;
        Object object = objects[0];
        for (int i = 0; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        if (size == cap) objects[objects.length - 1] = null;
        size--;
        return (E) object;
    }

    @Override
    public E element() {
        if (size == 0) throw new NoSuchElementException();
        return (E) objects[0];
    }

    @Override
    public E peek() {
        if (size == 0) return null;
        return (E) objects[0];
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "cap=" + cap +
                ", size=" + size +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
