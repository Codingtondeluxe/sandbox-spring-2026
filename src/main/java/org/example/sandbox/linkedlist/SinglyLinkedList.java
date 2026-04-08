package org.example.sandbox.linkedlist;

import java.util.StringJoiner;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // O(1)
    @Override
    public void addFirst(E element) {

        Node newNode = new Node(element, head); // 1, 2

        // 3
        if (head == null) { // empty list
            head = newNode;
            tail = newNode;
        } else {
            head = newNode;
        }
        this.size++;
    }

    // O(1)
    @Override
    public void addLast(E element) {

        Node newNode = new Node(element, null); // 1

        if (tail == null) { // empty list
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode; // 2
            tail = newNode; // 3
        }
        this.size++;
    }

    // O(1)
    @Override
    public E pollFirst() {

        E element;

        if (head == null) {
            element = null; // 1
        } else {
            element = head.element; // 1

            if (head == tail) { // 4
                head = null;
                tail = null;
            } else {
                Node next = head.next; // 2
                head.next = null; // 3
                head = next; // 4
            }
        }
        this.size--;
        return element;
    }

    // O(n)
    @Override
    public E pollLast() {

        E element;

        if (tail == null) {
            element = null;
        } else {
            element = tail.element; // 1

            if (head == tail) { // 3
                head = null;
                tail = null;
            } else {
                Node current = head;
                Node previous = head;
                while (current != tail) { // O(n)
                    previous = current; // 2
                    current = current.next; // 2
                }
                tail = previous; // 3
                tail.next = null; // 4
            }
        }
        this.size--;
        return element;
    }

    // O(1)
    @Override
    public E peekFirst() {
        return this.head.element;
    }

    // O(1)
    @Override
    public E peekLast() {
        return this.tail.element;
    }

    // O(n)
    @Override
    public void clear() {

        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null; // breaks reference
            current = next;
        }
        head = null;
        tail = null;
        this.size = 0;
    }

    // O(n)
    @Override
    public boolean contains(E element) {

        boolean contains = false;

        Node current = head;
        while (current != null) {
           if(current.element.equals(element)) {
               contains = true;
               break;
           }
           current = current.next;
        }

        return contains;
    }

    // O(1)
    @Override
    public int size() {
        return this.size;
    }

    // O(n^2)
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("[");
        StringJoiner joiner = new StringJoiner(", ");

        Node current = head;
        if (current != null) {

            while (current != null) {
                joiner.add(current.element.toString());
                current = current.next;
            }
        }
        builder.append(joiner);
        builder.append("]");
        return builder.toString();

    }

    private class Node {

        E element;
        Node next;


        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
