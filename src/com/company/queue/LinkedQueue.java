package com.company.queue;

import com.company.list.MyDeqLinkedList;
import com.company.list.MyLinkedList;

import java.util.NoSuchElementException;

public class LinkedQueue<E> extends MyDeqLinkedList<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private final int cap;

    public LinkedQueue() {
        this.cap = DEFAULT_CAPACITY;
    }

    public LinkedQueue(int initialCapacity) {
        this.cap = initialCapacity;
    }

    public static void main(String[] args) {
        Queue<String> stringQueue = new LinkedQueue<>();
        stringQueue.add("1");
        stringQueue.add("2");
        stringQueue.add("3");
        stringQueue.add("4");
        stringQueue.add("5");

        System.out.println(stringQueue);

        System.out.println(stringQueue.remove());
        System.out.println(stringQueue.remove());

        System.out.println(stringQueue);


        System.out.println(stringQueue.peek());
    }


    @Override
    public boolean add(E e) {
        if (super.size() == cap) throw new IllegalStateException();
        return super.add(e);
    }

    @Override
    public boolean offer(E e) {
        return super.add(e);
    }

    @Override
    public E remove() {
        if (super.size() == cap) throw new NoSuchElementException();
        return super.remove(0);
    }

    @Override
    public E poll() {
        return super.remove(0);
    }

    @Override
    public E element() {
        if (super.size() == cap) throw new NoSuchElementException();
        return super.get(0);
    }

    @Override
    public E peek() {
        return super.get(0);
    }
}
