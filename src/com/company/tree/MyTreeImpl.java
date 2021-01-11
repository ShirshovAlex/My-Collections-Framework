package com.company.tree;

import java.util.Comparator;
import java.util.Iterator;

public class MyTreeImpl<E> implements MyTree<E>, Iterable<E> {

    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    public MyTreeImpl(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        MyTreeImpl<User> eMyTree = new MyTreeImpl<>(new UserAgeAscComparator());

        eMyTree.add(new User(2, "2", 12));
        eMyTree.add(new User(3, "3", 6));
        eMyTree.add(new User(1, "1", 22));
        eMyTree.add(new User(4, "4", 16));
        eMyTree.add(new User(5, "5", 44));
        eMyTree.add(new User(5, "5", 80));
        eMyTree.add(new User(5, "5", 80));

        System.out.println(eMyTree);

        System.out.println(eMyTree.first());
        System.out.println(eMyTree.last());
    }

    @Override
    public void add(E e) {
        if (size == 0) {
            this.root = new Node<>(e, null, null, null);
            size++;
        } else {
            Node<E> temp = this.root;
            while (true) {
                if (comparator.compare(temp.element, e) == 0) break;
                if (comparator.compare(temp.element, e) < 0) {
                    if (temp.right == null) {
                        temp.right = new Node<>(e, null, null, temp);
                        size++;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
                if (comparator.compare(temp.element, e) > 0) {
                    if (temp.left == null) {
                        temp.left = new Node<>(e, null, null, temp);
                        size++;
                        break;
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }
    }

    @Override
    public E first() {
        Node<E> temp = this.root;

        while (true) {
            if (temp.left == null) {
                return temp.element;
            } else {
                temp = temp.left;
            }
        }
    }

    @Override
    public E last() {
        Node<E> temp = this.root;

        while (true) {
            if (temp.right == null) {
                return temp.element;
            } else {
                temp = temp.right;
            }
        }
    }

    @Override
    public String toString() {
        return "MyTreeImpl{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(this.root);
    }

    private static class Itr<E> implements Iterator<E> {
        Node<E> marker;
        boolean isLeft = true;

        public Itr(Node<E> root) {
            this.marker = root;
            setMarkerToStartPoint();
        }



        private void setMarkerToStartPoint() {
            while (true) {
                if (marker.left == null) {
                    break;
                } else {
                    marker = marker.left;
                }
            }
        }

        @Override
        public boolean hasNext() {
            Node<E> temp = this.marker;
            while ((true)){

            }

//            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
