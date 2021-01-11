package com.company.queue;

import com.company.list.MyDeqLinkedList;

public class LinkedDeque<E> extends MyDeqLinkedList<E> implements Deque<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private final int cap;

    public static void main(String[] args) {
        LinkedDeque<String> strings = new LinkedDeque<>();

        // TODO: 17.08.2020
//        strings.addFirst("5");
//        strings.addFirst("4");
//        strings.addFirst("3");
//        strings.addFirst("2");
//        strings.addFirst("1");

        strings.addLast("1");
        strings.addLast("2");
        strings.addLast("3");
        strings.addLast("4");
        strings.addLast("5");

        System.out.println(strings);

        // TODO: 17.08.2020
       // System.out.println(strings.removeFirst());
        // TODO: 17.08.2020
        System.out.println(strings.removeLast());

      //  System.out.println(strings.peekFirst());

        // TODO: 17.08.2020
        System.out.println(strings.peekLast());

        System.out.println(strings);
    }

    public LinkedDeque() {
        this.cap = DEFAULT_CAPACITY;
    }

    public LinkedDeque(int cap) {
        this.cap = cap;
    }

    public LinkedDeque(MyDeqLinkedList<E> es) {
        super(es);
        this.cap = es.size();
    }

    @Override
    public void addFirst(E e) {
        if(cap == super.size()) throw new IllegalStateException();
        super.add(0, e);
    }

    @Override
    public void addLast(E e) {
        if(cap == super.size()) throw new IllegalStateException();
        super.add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        if(cap == super.size()) return false;
        super.add(0,e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(cap == super.size()) return false;
        super.add(e);
        return false;
    }

    @Override
    public E removeFirst() {
        if(size() == 0) throw new IllegalStateException();
        E remove = super.remove(0);
        return remove;
    }

    @Override
    public E removeLast() {
        if(size() == 0) throw new IllegalStateException();
        E remove = super.remove(super.size());
        return remove;
    }

    @Override
    public E pollFirst() {
        if(size() == 0) return null;
        E remove = super.remove(0);
        return remove;
    }

    @Override
    public E pollLast() {
        if(size() == 0) return null;
        E remove = super.remove(super.size());
        return remove;
    }

    @Override
    public E peekFirst() {
        if(size() == 0) throw new IllegalStateException();
        E e = super.get(0);
        return e;
    }

    @Override
    public E peekLast() {
        if(size() == 0) throw  new IllegalStateException();
        E e = super.get(super.size());
        return e;
    }

    @Override
    public E elementFirst() {
        if(size() == 0) return null;
        E e = super.get(0);
        return e;
    }

    @Override
    public E elementLast() {
        if(size() == 0) return null;
        E e = super.get(super.size());
        return e;
    }
}
