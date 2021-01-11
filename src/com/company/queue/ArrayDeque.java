package com.company.queue;

import com.company.list.MyArrayList;

import java.util.NoSuchElementException;

public class ArrayDeque<E> extends MyArrayList<E> implements Deque<E> {

    private final static int DEFAULT_CAPACITY = 10;
    private final int cap;

    public static void main(String[] args) {
        ArrayDeque<String> strings = new ArrayDeque<>();

        // TODO: 14.08.2020 method addFirst  prov. ArrayIndexOfBoundsException
        // TODO: 14.08.2020 Impl. LinkedDeque ex MyDeqLinkedList
//        strings.addFirst("1");
//        strings.addFirst("2");
//        strings.addFirst("3");
//        strings.addFirst("4");

        strings.addLast("1");
        strings.addLast("2");
        strings.addLast("3");
        strings.addLast("4");
//
        System.out.println(strings);

//        System.out.println(strings.removeFirst());
//        System.out.println(strings.removeFirst());

//        System.out.println(strings.removeLast());
//        System.out.println(strings.removeLast());

//        System.out.println(strings);

//        System.out.println(strings.peekFirst());
//        System.out.println(strings.peekLast());
    }

    public ArrayDeque(int initialCapacity) {
        this.cap = initialCapacity;
    }

    public ArrayDeque() {
        this.cap = DEFAULT_CAPACITY;
    }

    @Override
    public void addFirst(E e) {
        if (super.size() == cap) throw new IllegalStateException();
        super.add(0, e);
    }

    @Override
    public void addLast(E e) {
        if (super.size() == cap) throw new IllegalStateException();
        super.add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        if (super.size() == cap) return false;
        super.add(0, e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (super.size() == cap) return false;
        super.add(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (super.size() == cap) throw new NoSuchElementException();
        return super.remove(0);
    }

    @Override
    public E removeLast() {
        if (super.size() == cap) throw new NoSuchElementException();
        return super.remove(size() - 1);
    }

    @Override
    public E pollFirst() {
        if (super.size() == cap) return null;
        return super.remove(0);
    }

    @Override
    public E pollLast() {
        if (super.size() == cap) return null;
        return super.remove(size() - 1);
    }

    @Override
    public E peekFirst() {
        if(super.size()==cap) throw new NoSuchElementException();
        return super.get(0);
    }

    @Override
    public E peekLast() {
        if(super.size()==cap) throw new NoSuchElementException();
        return super.get(size()-1);
    }

    @Override
    public E elementFirst() {
        if(super.size()==cap) return null;
        return super.get(0);
    }

    @Override
    public E elementLast() {
        if(super.size()==cap) return null;
        return super.get(size()-1);
    }
}
