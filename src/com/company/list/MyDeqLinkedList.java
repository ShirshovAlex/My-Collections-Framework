package com.company.list;


import java.util.Iterator;
import java.util.ListIterator;

public class MyDeqLinkedList<E> implements MyList<E> {
    private int size = 0;
    private Node<E> head;
    private Node<E> tail;

    public MyDeqLinkedList(MyDeqLinkedList<E> es) {
        for (int i = 0; i < es.size; i++) {
            this.add(es.get(i));
        }
    }

    public MyDeqLinkedList() {
    }

    public static void main(String[] args) {
        MyDeqLinkedList<String> myDeqLinkedList = new MyDeqLinkedList<String>();
        myDeqLinkedList.add("1");
        myDeqLinkedList.add("2");
        myDeqLinkedList.add("3");
        myDeqLinkedList.add("4");

//        Iterator<String> iterator = myDeqLinkedList.iterator();
//        iterator.next();
//        iterator.next();
//        iterator.remove();

        ListIterator<String> stringListIterator = myDeqLinkedList.listIterator();
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.previous());
        stringListIterator.add("9");

        System.out.println(myDeqLinkedList);


        myDeqLinkedList.remove(3);


        System.out.println(myDeqLinkedList);
    }

    @Override
    public boolean add(E o) {
        if (size == 0) {
            Node<E> node = new Node<>(o, null, null);
            this.head = node;
            this.tail = node;
        } else {
            Node<E> objectNode = new Node<>(o, null, this.tail);
            Node<E> tail = this.tail;
            tail.setNext(objectNode);
            this.tail = objectNode;
        }
        size++;
        return true;
    }

    private void checkIndex(int index) {
        if (index > size) throw new IndexOutOfBoundsException();
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index < size / 2) {
            Node<E> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return (E) temp.element;
                }
                temp = temp.next;
            }
        } else {
            Node<E> temp = tail;
            for (int i = size; i > 0; i--) {
                if (i == index) {
                    return (E) temp.element;
                }
                temp = temp.prev;
            }
        }

        return null;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        // ????????????????????????????????????? element
        if(index == size){
            Node<E> temp = tail;
            temp.prev.setNext(null);
            temp.setPrev(null);
            size --;
            return temp.getElement();
        }
//        if(index == 0){
//            Node<E> temp = head;
//            temp.setNext(null);
//            temp.next.setPrev(null);
//            size--;
//            return temp.getElement();
//        }
        if (index < size / 2) {
            Node<E> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    // TODO: 17.08.2020  
                    temp.prev.setNext(temp.next);
                    temp.next.setPrev(temp.prev);
                    temp.setNext(null);
                    temp.setPrev(null);
                    size--;
                    break;
                }
                temp = temp.next;
            }
        } else {
            Node<E> temp = tail;
            for (int i = size - 1; i > 0; i--) {
                if (i == index) {
                    temp.prev.setNext(temp.next);
                    temp.next.setPrev(temp.prev);
                    temp.setNext(null);
                    temp.setPrev(null);
                    size--;
                    break;
                }
                temp = temp.prev;
            }
        }
        return null;
    }

    @Override
    public E remove(E o) {
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.getElement().equals(o)) {
                temp.prev.setNext(temp.next);
                temp.next.setPrev(temp.prev);
                temp.setNext(null);
                temp.setPrev(null);
                size--;
                break;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public E set(int index, E o) {
        checkIndex(index);
        if (index < size / 2) {
            Node<E> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    Object old = temp.getElement();
                    temp.setElement(o);
                    return (E) old;
                }
                temp = temp.next;
            }
        } else {
            Node<E> temp = tail;
            for (int i = size - 1; i > 0; i--) {
                if (i == index) {
                    Object old = temp.getElement();
                    temp.setElement(o);
                    return (E) old;
                }
                temp = temp.prev;
            }
        }
        return null;
    }

    // TODO: 17.08.2020  
    @Override
    public void add(int index, E o) {
        checkIndex(index);
        if (index < size / 2) {
            Node<E> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    Node<E> center = temp;
                    Node<E> left = center.prev;
                    Node<E> newNode = new Node<>(o, center, left);
                    center.setPrev(newNode);
                    left.setNext(newNode);
                    size++;
                    break;
                }
                temp = temp.next;
            }
        } else {
            Node<E> temp = tail;
            for (int i = size - 1; i > 0; i--) {
                if (i == index) {
                    Node<E> center = temp;
                    Node<E> left = center.prev;
                    Node<E> newNode = new Node<>(o, center, left);
                    center.setPrev(newNode);
                    left.setNext(newNode);
                    size++;
                    break;
                }
                temp = temp.prev;
            }
        }
    }

    @Override
    public boolean contains(E o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(E o) {
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.getElement().equals(o)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Node<E> temp = head;
        Object[] mass = new Object[size];
        for (int i = 0; i < size; i++) {
            mass[i] = temp.getElement();
            temp = temp.next;
        }
        return mass;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>(new MyDeqLinkedList<>(this), this);
    }

    @Override
    public String toString() {
        return "MyDeqLinkedList{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(new MyDeqLinkedList<>(this), this);
    }

    private static class ListItr<E> extends Itr<E> implements ListIterator<E> {

        public ListItr(MyDeqLinkedList<E> list, MyDeqLinkedList<E> origin) {
            super(list, origin);
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
            origin.set(cursor - 1, e);
        }

        @Override
        public void add(E e) {
            origin.add(cursor - 1, e);
        }
    }

    private static class Itr<E> implements Iterator<E> {
        MyDeqLinkedList<E> list;
        MyDeqLinkedList<E> origin;
        int cursor = 0;

        public Itr(MyDeqLinkedList<E> list, MyDeqLinkedList<E> origin) {
            this.list = list;
            this.origin = origin;
        }

        @Override
        public boolean hasNext() {
            if (list.size == 0) return false;
            if (list.size == cursor) return false;
            return list.get(cursor) != null;
        }

        @Override
        public E next() {
            return list.get(cursor++);
        }

        @Override
        public void remove() {
            if (cursor != 0) {
                origin.remove(cursor - 1);
            }
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node() {
        }

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
//                    ", prev=" + prev +
                    '}';
        }
    }
}
