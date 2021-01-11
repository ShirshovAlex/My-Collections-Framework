package com.company.list;

import java.util.ListIterator;

public interface MyList<E> extends Iterable<E> {
    boolean add(E o);
    E get(int index);
    E remove(int index);
    E remove(E o);
    E set(int index, E o);
    void add(int index, E o);
    boolean contains(E o);
    int indexOf(E o);
    Object[] toArray();
    int size();
    void clear();

    ListIterator<E> listIterator();
}
