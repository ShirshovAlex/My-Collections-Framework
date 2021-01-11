package com.company.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private int size = 0;
    private Object[] objects;


    public MyArrayList() {
        this.objects = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        this.objects = new Object[capacity];
    }

    public MyArrayList(MyList<E> copy) {
        Object[] objects = copy.toArray();
        this.objects = objects;
        this.size = objects.length;
    }

    public static void main(String[] args) {
        MyArrayList<String> abc = new MyArrayList<>();
        abc.add("1");
        abc.add("2");
        abc.add("3");
        abc.add("4");

//        Iterator<String> iterator = abc.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next().equals("2")){
//                iterator.remove();
//            }
//        }

        ListIterator<String> stringListIterator = abc.listIterator();
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.next());
//        System.out.println(stringListIterator.hasPrevious());
        System.out.println(stringListIterator.previous());
        stringListIterator.add("9");

        System.out.println(abc);
    }

    private void grow() {
        if (objects.length == 0) {
            objects = Arrays.copyOf(objects, DEFAULT_CAPACITY);
        } else {
            int oldCapacity = objects.length;
            int i = oldCapacity / 2;
            int newCapacity = oldCapacity + i;
            if (newCapacity < 0) {
                throw new OutOfMemoryError();
            }
            objects = Arrays.copyOf(objects, newCapacity);
        }
    }

    @Override
    public boolean add(E o) {
        if (size == objects.length) grow();
        objects[size++] = o;
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > objects.length) {
            throw new IndexOutOfBoundsException("Index " + index + " > " + "array length " + objects.length);
        }
    }


    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) objects[index];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Object old = objects[index];
        if (objects.length - 1 - index >= 0)
            System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
        size--;
        return (E) old;
    }

    @Override
    public E remove(E o) {
        Object old = null;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) {
                old = objects[i];
                if (objects.length - 1 - i >= 0) System.arraycopy(objects, i + 1, objects, i, objects.length - 1 - i);
            }
        }
        size--;
        return (E) old;
    }

    @Override
    public E set(int index, E o) {
        checkIndex(index);
        Object old = objects[index];
        objects[index] = o;
        return (E) old;
    }

    @Override
    public void add(int index, E o) {
        checkIndex(index);
        if (size == objects.length) grow();
        if (objects.length - index >= 0) System.arraycopy(objects, index - 1, objects, index, objects.length - index);
        objects[index] = o;
        size++;
    }

    @Override
    public boolean contains(E o) {
        for (Object object : objects) {
            if (object.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objects, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        objects = EMPTY_ELEMENT_DATA;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>(new MyArrayList<>(this), this);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(new MyArrayList<>(this), this);
    }

    private static class ListItr<E> extends Itr<E> implements ListIterator<E>{

        public ListItr(MyList<E> list, MyList<E> original) {
            super(list, original);
        }

        @Override
        public boolean hasPrevious() {
            return cursor >= 1;
        }

        @Override
        public E previous() {
            return list.get(cursor - 1);
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            original.set(cursor - 1, e);
        }

        @Override
        public void add(E e) {
            original.add(cursor - 1, e);
        }
    }

    private static class Itr<E> implements Iterator<E> {
        MyList<E> list;
        MyList<E> original;
        int cursor = 0;

        public Itr(MyList<E> list, MyList<E> original) {
            this.list = list;
            this.original = original;
        }

        @Override
        public boolean hasNext() {
            if (list.size() == cursor) return false;
            return list.get(cursor) != null;
        }

        @Override
        public E next() {
            return list.get(cursor++);
        }

        @Override
        public void remove() {
            if (cursor != 0) {
                original.remove(cursor - 1);
            }
        }
    }
}
