package com.company.queue;

public interface Deque<E> {
    void addFirst(E e);
    void addLast(E e);
    boolean offerFirst(E e);
    boolean offerLast(E e);

    E removeFirst();
    E removeLast();
    E pollFirst();
    E pollLast();

    E peekFirst();
    E peekLast();
    E elementFirst();
    E elementLast();
}
