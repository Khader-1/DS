package structures;

import java.util.ArrayList;
import java.util.List;

import models.Model;


public class LinkedList<T extends Model> {
    public class Node<NT extends Model> {
        public final NT value;
        public Node<NT> next;
    
        Node(NT value, Node<NT> next) {
            this.value = value;
            this.next = next;
        }
    }
    
    int length = 0;
    Node<T> firstNode;
    Node<T> lastNode;

    public Node<T> first() {
        return firstNode;
    }

    public Node<T> last() {
        return lastNode;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void insertFirst(Node<T> node) {
        if (firstNode == null) {
            firstNode = node;
            lastNode = node;
        } else {
            node.next = firstNode;
            firstNode = node;
        }
        length++;
    }

    public void insertFirst(T value) {
        insertFirst(new Node<>(value, null));
    }

    public void insertAfter(Node<T> node, Node<T> newNode) {
        if (node == lastNode) {
            insertLast(newNode);
        } else {
            newNode.next = node.next;
            node.next = newNode;
            length++;
        }
    }

    public void insertAfter(Node<T> node, T value) {
        insertAfter(node, new Node<>(value, null));
    }
    

    public void insertLast(Node<T> node) {
        if (firstNode == null) {
            firstNode = node;
            lastNode = node;
        } else {
            lastNode.next = node;
            lastNode = node;
        }
        length++;
    }

    public void insertLast(T value) {
        insertLast(new Node<>(value, null));
    }

    public void removeFirst() {
        if (firstNode == null) {
            System.out.println("there is no element");
        } else if (firstNode.next == null) {
            firstNode = null;
        } else {
            firstNode = firstNode.next;
        }
        length--;
    }

    public void removeLast() {
        Node<T> n;
        if (firstNode == null) {
            System.out.println("wrong");
        } else if (firstNode.next == null) {
            firstNode = null;
        } else {
            for (n = firstNode; n.next != lastNode; n = n.next) {

            }
            lastNode = n;
            lastNode.next = null;
            length--;

        }
    }

    public void removeAfter(Node<T> node) {
        if (node == lastNode) {
            System.out.println("wrong");
        } else if (node.next == lastNode) {
            lastNode = node;
            lastNode.next = null;
            length--;
        } else {
            node.next = node.next.next;
            length--;
        }
    }


    public void printList() {

        for (Node<T> n = firstNode; n != null; n = n.next) {
            System.out.println(n.value);
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<T>();
        for (Node<T> n = firstNode; n != null; n = n.next) {
            list.add((T) n.value);
        }
        return list;
    }

}
