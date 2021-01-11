package com.company.list;

import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Node<E> root;

    public MyLinkedList(MyList<E> es) {
        for (int i = 0; i < es.size(); i++) {
            this.add(es.get(i));
        }
    }

    public MyLinkedList() {
    }

    public static void main(String[] args) {
        MyLinkedList<String> strings = new MyLinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");

//        Iterator<String> iterator = strings.iterator();
//        iterator.next();
//        iterator.next();
//        iterator.remove();

        ListIterator<String> stringListIterator = strings.listIterator();
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.previous());

        stringListIterator.set("9");

        System.out.println(strings);

//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

//        System.out.println(strings);

//        MyLinkedList<String> copy = new MyLinkedList<>(strings);

//        System.out.println(copy);
    }

    private void checkIndex(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean add(E o) {
        if (size == 0) {
            this.root = new Node<E>(o, null);
            size++;
            return true;
        } else {
            Node<E> temp = root;
            while (true) {
                if (temp.next == null) {
                    temp.next = new Node<E>(o, null);
                    size++;
                    return true;
                }
                temp = temp.next;
            }
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return (E) temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> temp = root;
        if (index == 0){
            E element = root.getValue();
            root = root.next;
            size--;
            return element;
        }
        for (int i = 0; i < size; i++) {
            if ((i + 1) == index) {
                Node<E> right = temp.next.next;
                Node<E> current = temp.next;
                Node<E> left = temp;

                Object old = current.getValue();
                current.setValue(null);
                current.setNext(null);

                left.setNext(right);
                size--;
                return (E) old;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public E remove(E o) {
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (temp.next.getValue().equals(o)) {
                Node<E> right = temp.next.next;
                Node<E> current = temp.next;
                Node<E> left = temp;

                Object old = current.getValue();
                current.setValue(null);
                current.setNext(null);

                left.setNext(right);
                size--;
                return (E) old;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public E set(int index, E o) {
        checkIndex(index);
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                Object old = temp.getValue();
                temp.setValue(o);
                return (E) old;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void add(int index, E o) {
        checkIndex(index);
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (i + 1 == index) {
                Node<E> current = temp.next;

                Node<E> node = new Node<>(o, current);
                Node<E> left = temp;

                left.setNext(node);
                size++;
            }
            temp = temp.next;
        }
    }

    // TODO: 27.07.2020 home
    @Override
    public boolean contains(E o) {
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (temp.getValue().equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // TODO: 27.07.2020 home
    @Override
    public int indexOf(E o) {
        Node<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (temp.getValue().equals(o)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public E[] toArray() {
        Object[] mass = new Object[size];
        Node<E> temp = root;
        for (int i = 0; i < mass.length; i++) {
            mass[i] = (E) temp.getValue();
            temp = temp.next;
        }
        return (E[]) mass;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>(new MyLinkedList<>(this), this);
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", root=" + root +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(new MyLinkedList<>(this), this);
    }

    public static class ListItr<E> extends Itr<E> implements ListIterator<E> {

        public ListItr(MyList<E> list, MyList<E> original) {
            super(list, original);
        }

        @Override
        public boolean hasPrevious() {
            return list.get(cursor - 1) != null;
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
            if (list.size() == 0) return false;
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

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
