package com.company.queue;

import java.util.Arrays;

public class Stack<E> {
    private Object[] objects;
    private int size;

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        stack.push("4");
        stack.push("3");
        stack.push("2");
        stack.push("1");
        System.out.println(stack);

        System.out.println(stack.pop());

        System.out.println(stack);

        System.out.println(stack.search("4"));
    }


    public Stack() {
        this.objects = new Object[10];
    }

    public Stack(int initSize) {
        this.objects = new Object[initSize];
    }

    public E push(E element) {
        objects[size++] = element;
        return element;
    }

    public E pop() {
        E object = (E) objects[size - 1];
        objects[size - 1] = null;
        size--;
        return object;
    }

    public E peek() {
        return (E) objects[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(Object o){
        for (int i = size - 1, j = 0; i >= 0; i--, j++) {
            if (objects[i].equals(o)) {
                return j;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "objects=" + Arrays.toString(objects) +
                ", size=" + size +
                '}';
    }
}
